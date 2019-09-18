package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_profile

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

class TraderEditProfilePresenter(
    private val mView: TraderEditProfileMainContracts.IView,
    private val context: Context
) : TraderEditProfileMainContracts.IPresenter {
    private lateinit var mModel: TraderEditProfileMainContracts.IModel
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var phone: String
    private lateinit var traderID: String

    override fun initValidation(firstName: String, lastName: String, phone: String) {
        mModel = TraderEditProfileModel(firstName, lastName, phone)
        if (mModel.validate()) {
            this.firstName = firstName
            this.lastName = lastName
            this.phone = phone
            this.traderID = context.getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("id", "n/a") as String
            mView.onValidationResult(true)
        } else {
            mView.onValidationResult(false)
        }
    }

    override fun editProfile() {
        val pd =
            MainUtils.getLoadingDialog(context, "Please Wait", "Updating your profile", false)
        pd.show()
        val API = "http://squadtechsolution.com//android/v1/trader_login.php"
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
                        editor.putString("first_name", firstName)
                        editor.putString("last_name", lastName)
                        editor.putString("phone", phone)
                        editor.apply()
                        mView.onEditProfileResult(true)
                    } else {
                        mView.onValidationResult(false)
                    }
                } catch (exc: Exception) {
                    mView.onEditProfileResult(false)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onEditProfileResult(false)
                Log.i("dxdiag_error", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["firstName"] = firstName
                map["lastName"] = lastName
                map["mobile"] = phone
                map["id"] = traderID
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

}