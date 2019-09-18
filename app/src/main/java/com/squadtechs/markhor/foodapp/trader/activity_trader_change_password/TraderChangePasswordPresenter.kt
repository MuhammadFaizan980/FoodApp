package com.squadtechs.markhor.foodapp.trader.activity_trader_change_password

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

class TraderChangePasswordPresenter(
    private val mView: TraderChangePasswordMainContracts.IView,
    private val context: Context
) : TraderChangePasswordMainContracts.IPresenter {
    private lateinit var mModel: TraderChangePasswordMainContracts.IModel
    private lateinit var newPassword: String
    private lateinit var traderID: String

    override fun initValidation(
        currentPassword: String,
        savedCurrentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ) {
        mModel = TraderChangePasswordModel(
            currentPassword,
            savedCurrentPassword,
            newPassword,
            confirmNewPassword
        )
        if (mModel.validate()) {
            this.newPassword = newPassword
            this.traderID = context.getSharedPreferences(
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
        val API = "http://squadtechsolution.com//android/v1/update_trader_password.php"
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
                map["trader_id"] = traderID
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

}