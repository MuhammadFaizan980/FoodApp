package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class DeliveryDetailsPresenter(
    private val mView: DeliveryDetailsContracts.IView,
    private val context: Context
) : DeliveryDetailsContracts.IPresenter {

    private val API = "http://squadtechsolution.com/android/v1/company_deliveryDetail.php"
    private var deliver: Boolean = false
    private lateinit var range: String
    private lateinit var pickupInformation: String
    private lateinit var mModel: DeliveryDetailsContracts.IModel

    override fun initValidation(deliver: Boolean, range: String, pickupInformation: String) {
        mModel = DeliveryDetailsModel(deliver, range, pickupInformation)
        this.deliver = deliver
        this.range = range
        this.pickupInformation = pickupInformation
        mView.onValidationResult(mModel.validate())
    }

    override fun addDeliveryDetails() {
        val pd = ProgressDialog(context)
        pd.setTitle("Please Wait")
        pd.setMessage("Adding company timings")
        pd.setCancelable(false)
        pd.show()
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("resp", response)
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Log.i("resp", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                val trader_id =
                    context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                        .getString("trader_id", "n/a") as String
                map["delivery_Range"] = range
                map["delivery_Type"] = if (deliver) {
                    "yes"
                } else {
                    "no"
                }
                map["delivery_pickupinfo"] = pickupInformation
                map["trader_id"] = trader_id
                Log.i("delivery_details", map.toString())
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
}