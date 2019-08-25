package com.squadtechs.markhor.foodapp.trader.trader_registration

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class TraderRegistrationPresenter(
    private val mView: TraderRegistrationContracts.IView,
    private val context: Context
) : TraderRegistrationContracts.IPresenter {

    private val API: String = "http://squadtechsolution.com//android/v1/trader_registration.php"
    private lateinit var mModel: TraderRegistrationContracts.IModel
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var mobileNumber: String
    private lateinit var companyMobile: String
    private lateinit var email: String
    private lateinit var password: String
    private val status: String = "pending"

    override fun initValidation(
        firstName: String,
        lastName: String,
        mobileNumber: String,
        companyMobile: String,
        email: String,
        password: String,
        confirmPassword: String,
        agreement: Boolean
    ) {
        mModel = TraderRegistrationModel(
            firstName,
            lastName,
            mobileNumber,
            companyMobile,
            email,
            password,
            confirmPassword,
            agreement
        )
        if (mModel.validate()) {
            this.firstName = firstName
            this.lastName = lastName
            this.mobileNumber = mobileNumber
            this.companyMobile = companyMobile
            this.email = email
            this.password = password
        }
        mView.onValidationResult(mModel.validate())
    }

    override fun initRegistration() {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait")
        progressDialog.setTitle("Message!")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val requestQueue = Volley.newRequestQueue(context)
        val strinRequest = object : StringRequest(
            Request.Method.POST,
            API,
            Response.Listener { response ->
                progressDialog.cancel()
                val json = JSONObject(response)
                if (json.getString("status").equals("reg_failed")) {
                    mView.onRegistrationResult(false)
                } else {
                    showDialog()
                }
            },
            Response.ErrorListener { error ->
                progressDialog.cancel()
                mView.onRegistrationResult(false)
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["email"] = email
                map["password"] = password
                map["firstname"] = firstName
                map["lastname"] = lastName
                map["mobile"] = mobileNumber
                map["Company_mobile"] = companyMobile
                map["status"] = status
                return map
            }
        }
        requestQueue.add(strinRequest)
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Message!")
        dialog.setMessage("Trader account created successfully\nYour account is pending for approval by admin")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Close") { dialogInterface, i ->
            dialogInterface.cancel()
        }
        dialog.show()
    }

}