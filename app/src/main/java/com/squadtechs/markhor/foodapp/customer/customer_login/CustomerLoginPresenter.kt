package com.squadtechs.markhor.foodapp.customer.customer_login

import android.content.Context
import android.content.SharedPreferences
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
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
        mModel = CustomerLoginModel(this, this.email, this.password)
        mModel.validateFields()
    }

    override fun validationCallback(status: Boolean, message: String) {
        mView.onValidationResult(status, message)
    }

    override fun initLogin() {
        val progressDialog = MainUtils.getLoadingDialog(context, "Message!", "Please wait", false)
        progressDialog.show()
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            API,
            Response.Listener { response ->
                val json = JSONObject(response)
                if (json.getString("status").equals("login_failed")) {
                    progressDialog.cancel()
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
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(obj.email, obj.password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                mView.onLoginResult(true, "")
                            } else {
                                mView.onLoginResult(false, task.exception!!.message!!)
                                progressDialog.cancel()
                            }
                        }
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