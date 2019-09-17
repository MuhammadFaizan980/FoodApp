package com.squadtechs.markhor.foodapp.customer.activity_customer_food_company_details

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONArray

class CustomerFoodCompanyDetailsPresenter(
    private val mView: CustomerFoodCompanyDetailsContracts.IView,
    private val context: Context
) : CustomerFoodCompanyDetailsContracts.IPresenter {
    override fun getCompanyData(companyID: String) {
        val pd = MainUtils.getLoadingDialog(context, "Please Wait", "Loading company data", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/singleCompanyDetail.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                val json = JSONArray(response)[0]
                val obj: CustomerFoodCompanyDetailsResponseHandler = Gson().fromJson(
                    json.toString(),
                    CustomerFoodCompanyDetailsResponseHandler::class.java
                )
                mView.onGetCompanyDataResult(false, obj)
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onGetCompanyDataResult(true, null)
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["id"] = companyID
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

}