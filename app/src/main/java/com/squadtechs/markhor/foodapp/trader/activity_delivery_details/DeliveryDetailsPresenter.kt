package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

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
        mModel = DeliveryDetailsModel(range, pickupInformation)
        this.deliver = deliver
        this.range = range
        this.pickupInformation = pickupInformation
        mView.onValidationResult(mModel.validate())
    }

    override fun addDeliveryDetails() {
        val pd = MainUtils.getLoadingDialog(context, "Please Wait", "Adding data", false)
        pd.show()
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                try {
                    val json = JSONObject(response)
                    if (json.getString("status").equals("update_success")) {
                        mView.onAddDeliveryDetailsResult(true)
                    } else {
                        mView.onAddDeliveryDetailsResult(false)
                    }
                } catch (exc: Exception) {
                    mView.onAddDeliveryDetailsResult(false)
                }
                Log.i("resp", response)
            },
            Response.ErrorListener { error ->
                mView.onAddDeliveryDetailsResult(false)
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
                map["is-profile-complete"] = "yes"
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
}