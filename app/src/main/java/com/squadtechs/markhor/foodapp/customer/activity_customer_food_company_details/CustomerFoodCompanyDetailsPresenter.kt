package com.squadtechs.markhor.foodapp.customer.activity_customer_food_company_details

import android.app.ProgressDialog
import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONArray

class CustomerFoodCompanyDetailsPresenter(
    private val mView: CustomerFoodCompanyDetailsContracts.IView,
    private val context: Context,
    private val companyID: String
) : CustomerFoodCompanyDetailsContracts.IPresenter {
    override fun getCompanyData(companyID: String) {
        val pd = ProgressDialog(context)
        pd.setCancelable(false)
        pd.setTitle("Loading")
        pd.setMessage("Please wait")
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
                mView.onGetCompanyDataResult(true, obj)
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