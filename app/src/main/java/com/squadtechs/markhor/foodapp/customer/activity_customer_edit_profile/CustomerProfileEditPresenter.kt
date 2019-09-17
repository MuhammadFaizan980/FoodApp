package com.squadtechs.markhor.foodapp.customer.activity_customer_edit_profile

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.customer.util.CustomerUtils
import org.json.JSONObject

class CustomerProfileEditPresenter(
    private val mView: CustomerProfileEditContracts.IView,
    private val context: Context
) : CustomerProfileEditContracts.IPresenter {

    private lateinit var mModel: CustomerProfileEditContracts.IModel
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var phone: String
    private lateinit var customerID: String

    override fun initValidation(firstName: String, lastName: String, phone: String) {
        mModel = CustomerProfileEditModel(firstName, lastName, phone)
        if (mModel.validate()) {
            this.firstName = firstName
            this.lastName = lastName
            this.phone = phone
            this.customerID = context.getSharedPreferences(
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
            CustomerUtils.getLoadingDialog(context, "Please Wait", "Updating your profile", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/update_customer_details.php"
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
                map["customer_id"] = customerID
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
}