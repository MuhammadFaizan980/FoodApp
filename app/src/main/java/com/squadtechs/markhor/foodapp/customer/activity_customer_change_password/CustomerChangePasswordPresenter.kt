package com.squadtechs.markhor.foodapp.customer.activity_customer_change_password

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

class CustomerChangePasswordPresenter(
    private val mView: CustomerChangePasswordMainContracts.IView,
    private val context: Context
) : CustomerChangePasswordMainContracts.IPresenter {
    private lateinit var mModel: CustomerChangePasswordMainContracts.IModel
    private lateinit var newPassword: String
    private lateinit var customerID: String

    override fun initValidation(
        currentPassword: String,
        savedCurrentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ) {
        mModel = CustomerChangePasswordModel(
            currentPassword,
            savedCurrentPassword,
            newPassword,
            confirmNewPassword
        )
        if (mModel.validate()) {
            this.newPassword = newPassword
            this.customerID = context.getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("id", "n/a") as String
            mView.onValidationResult(true)
        } else {
            mView.onValidationResult(false)
        }
    }

    override fun changePassword() {
        val pd =
            MainUtils.getLoadingDialog(context, "Please Wait", "Updating your password", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/update_customer_password.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                val json = JSONObject(response)
                try {
                    if (json.getString("status").equals("update_success")) {
                        val pref =
                            context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putString("user_password", newPassword)
                        editor.putBoolean("customer_logged_in", false)
                        editor.apply()
                        mView.onChangePasswordResult(true)
                    } else {
                        mView.onValidationResult(false)
                    }
                } catch (exc: Exception) {
                    mView.onChangePasswordResult(false)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onChangePasswordResult(false)
                Log.i("dxdiag_error", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["password"] = newPassword
                map["customer_id"] = customerID
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

}