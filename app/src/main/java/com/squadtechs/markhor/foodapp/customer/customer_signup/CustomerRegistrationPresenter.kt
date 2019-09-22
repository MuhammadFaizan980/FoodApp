package com.squadtechs.markhor.foodapp.customer.customer_signup

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

class CustomerRegistrationPresenter(
    private val mView: CustomerRegistrationContracts.IView,
    private val context: Context
) : CustomerRegistrationContracts.IPresenter {

    private val API: String = "http://squadtechsolution.com//android/v1/customer_register.php"
    private lateinit var mModel: CustomerRegistrationContracts.IModel
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var mobileNumber: String
    private lateinit var birthday: String
    private lateinit var email: String
    private lateinit var password: String

    override fun initValidation(
        firstName: String,
        lastName: String,
        mobileNumber: String,
        birthday: String,
        email: String,
        password: String,
        confirmPassword: String,
        userAgreement: Boolean
    ) {
        mModel = CustomerRegistrationModel(
            firstName,
            lastName,
            mobileNumber,
            birthday,
            email,
            password,
            confirmPassword,
            userAgreement
        )
        if (mModel.validate()) {
            this.firstName = firstName
            this.lastName = lastName
            this.mobileNumber = mobileNumber
            this.birthday = birthday
            this.email = email
            this.password = password
        }
        mView.onValidationResult(mModel.validate())
    }

    override fun initRegistration() {
        val progressDialog = MainUtils.getLoadingDialog(context, "Message!", "Please wait", false)
        progressDialog.show()
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            API,
            Response.Listener { response ->
                val json = JSONObject(response)
                if (json.getString("status").equals("reg_failed")) {
                    progressDialog.cancel()
                    mView.onRegistrationResult(
                        false,
                        json.getString("label") + " try with different credentials"
                    )
                    Log.i("dxdiag", response)
                } else {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                mView.onRegistrationResult(true, "")
                            } else {
                                mView.onRegistrationResult(false, task.exception!!.message!!)
                                progressDialog.cancel()
                            }
                        }
                }
            },
            Response.ErrorListener { error ->
                progressDialog.cancel()
                Log.i("dxdiag", error.toString())
                mView.onRegistrationResult(false, "")
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["useremail"] = email
                map["userpassword"] = password
                map["fname"] = firstName
                map["lname"] = lastName
                map["phone"] = mobileNumber
                map["userbirthday"] = birthday
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

//    private fun showDialog() {
//        val dialog = AlertDialog.Builder(context)
//        dialog.setTitle("Message!")
//        dialog.setMessage("User created successfully\nYou can now login")
//        dialog.setCancelable(false)
//        dialog.setPositiveButton("Login") { dialogInterface, i ->
//            context.startActivity(Intent(context, ActivityCustomerLogin::class.java))
//            (context as Activity).finish()
//        }
//            .setNegativeButton("Close") { dialogInterface, i ->
//                dialogInterface.cancel()
//            }
//        dialog.show()
//    }

}