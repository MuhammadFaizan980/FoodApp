package com.squadtechs.markhor.foodapp.trader.trader_login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject


class TraderLoginPresenter(
    private val mView: TraderLoginContracts.IView,
    private val context: Context
) :
    TraderLoginContracts.IPresenter {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var mModel: TraderLoginContracts.IModel
    private val API: String = "http://squadtechsolution.com//android/v1/trader_login.php"
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun initValidation(email: String, password: String) {
        this.email = email
        this.password = password
        mModel = TraderLoginModel(this.email, this.password)
        mView.onValidationResult(mModel.validateFields())
    }

    override fun initLogin() {
        val progressDialog = MainUtils.getLoadingDialog(context, "Loading", "Please Wait", false)
        progressDialog.show()
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            API,
            Response.Listener { response ->
                Log.i("m_resp", response)
                val json = JSONObject(response)
                if (json.getString("status").equals("login_failed")) {
                    progressDialog.cancel()
                    mView.onLoginResult(
                        false,
                        "n/a",
                        "n/a",
                        json.getString("label") + " with other credentials"
                    )
                } else {
                    pref = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                    editor = pref.edit()
                    val obj: TraderLoginResponseModel =
                        Gson().fromJson(response, TraderLoginResponseModel::class.java)
                    editor.putString("id", obj.id)
                    editor.putString("first_name", obj.firstname)
                    editor.putString("last_name", obj.lastname)
                    editor.putString("user_email", obj.email)
                    editor.putString("user_password", obj.password)
                    editor.putString("phone", obj.mobile)
                    editor.putString("is_profile_complete", obj.is_profile_complete)
                    editor.putString("company_type", obj.company_type)
                    editor.putString("company_id", obj.company_id)
                    editor.apply()

                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(obj.email, obj.password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                progressDialog.cancel()
                                mView.onLoginResult(
                                    true,
                                    obj.account_status,
                                    obj.is_profile_complete,
                                    "n/a"
                                )
                            } else {
                                mView.onLoginResult(false, "n/a", "n/a", "n/a")
                                progressDialog.cancel()
                            }
                        }
                }
            },
            Response.ErrorListener { error ->
                Log.i("m_resp", error.toString())
                progressDialog.cancel()
                mView.onLoginResult(false, "n/a", "n/a", "n/a")
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