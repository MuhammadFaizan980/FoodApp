package com.squadtechs.markhor.foodapp.customer.customer_login

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class CustomerLoginPresenter(
    private val mView: CustomerLoginContracts.IView,
    private val context: Context
) :
    CustomerLoginContracts.IPresenter {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var mModel: CustomerLoginContracts.IModel
    private val API: String = "http://squadtechsolution.com//android/v1/register_login.php"
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun initValidation(email: String, password: String) {
        this.email = email
        this.password = password
        mModel = CustomerLoginModel(this.email, this.password)
        mView.onValidationResult(mModel.validateFields())
    }

    override fun initLogin() {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait")
        progressDialog.setTitle("Message!")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            API,
            Response.Listener { response ->
                progressDialog.cancel()
                val json = JSONObject(response)
                if (json.getString("status").equals("login_failed")) {
                    mView.onLoginResult(
                        false,
                        json.getString("label") + " try using different credentials"
                    )
                } else {
                    pref = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                    editor = pref.edit()
                    val obj: CustomerLoginResponseModel =
                        Gson().fromJson(response, CustomerLoginResponseModel::class.java)
                    editor.putString("id", obj.id)
                    editor.putString("first_name", obj.firstname)
                    editor.putString("last_name", obj.lastname)
                    editor.putString("user_email", obj.email)
                    editor.putString("user_password", obj.password)
                    editor.putString("birthday", obj.birthday)
                    editor.putString("phone", obj.phone)
                    editor.putBoolean("customer_logged_in", true)
                    editor.apply()
                    mView.onLoginResult(true, "")
                }
            },
            Response.ErrorListener { error ->
                progressDialog.cancel()
                mView.onLoginResult(false, "")
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["email"] = email
                map["password"] = password
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
}