package com.squadtechs.markhor.foodapp.customer.customer_signup

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import org.json.JSONObject

class CustomerRegistrationPresenter(
    private val mView: CustomerRegistrationCnotracts.IView,
    private val context: Context
) : CustomerRegistrationCnotracts.IPresenter {

    private val API: String = "http://squadtechsolution.com//android/v1/customer_register.php"
    private lateinit var mModel: CustomerRegistrationCnotracts.IModel
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
        val requestQueue = Volley.newRequestQueue(context)
        val strinRequest = object : StringRequest(
            Request.Method.POST,
            API,
            Response.Listener { response ->
                val json = JSONObject(response)
                if (json.getString("status").equals("reg_failed")) {
                    mView.onRegistrationResult(false)
                } else {
                    showDialog()
                }
            },
            Response.ErrorListener { error ->
                Log.i("dxdiag", error.toString())
                mView.onRegistrationResult(false)
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
        requestQueue.add(strinRequest)
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Message!")
        dialog.setMessage("User created successfully\nYou can now login")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Login") { dialogInterface, i ->
            context.startActivity(Intent(context, ActivityCustomerLogin::class.java))
            (context as Activity).finish()
        }
            .setNegativeButton("Close") { dialogInterface, i ->
                dialogInterface.cancel()
            }
        dialog.show()
    }

}