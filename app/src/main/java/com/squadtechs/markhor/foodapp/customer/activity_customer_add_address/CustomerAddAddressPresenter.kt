package com.squadtechs.markhor.foodapp.customer.activity_customer_add_address

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class CustomerAddAddressPresenter(
    private val mView: CustomerAddAddressMainContracts.IView,
    private val context: Context
) : CustomerAddAddressMainContracts.IPresenter {
    private lateinit var mModel: CustomerAddAddressMainContracts.IModel
    private lateinit var address: String
    private lateinit var defaultString: String
    private lateinit var customerID: String

    override fun initValidation(address: String, defaultString: String) {
        mModel = CustomerAddAddressModel(address)
        if (mModel.validate()) {
            this.address = address
            this.defaultString = defaultString
            this.customerID = context.getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("id", "n/a") as String
            mView.onValidationResult(true)
        } else {
            mView.onValidationResult(false)
        }
    }

    override fun saveCustomerAddress() {
        val pd = MainUtils.getLoadingDialog(context, "Please Wait", "Adding address", false)
        pd.show()
        val API = "http://squadtechsolution.com//android/v1/customer_address.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                Log.i("address_response", response)
                pd.cancel()
                try {
                    if (response.contains("Address Uploaded")) {
                        val pref =
                            context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putString("default_address", address)
                        editor.apply()
                        mView.onSaveAddressResult(true)
                    } else if (response.contains("You must make Atleast one Address As_default")) {
                        Toast.makeText(
                            context,
                            "At least one address must be made default",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        mView.onSaveAddressResult(false)
                    }
                } catch (exc: Exception) {
                    Log.i("dxdiag", exc.toString())
                    mView.onSaveAddressResult(false)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Log.i("dxdiag", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["customer_id"] = customerID
                map["address"] = address
                map["isDefault"] = defaultString
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
}