package com.squadtechs.markhor.foodapp.customer.activity_customer_food_company_details

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import com.squareup.picasso.Picasso
import org.json.JSONArray

class CustomerFoodCompanyDetailsPresenter(
    private val mView: CustomerFoodCompanyDetailsContracts.IView,
    private val context: Context
) : CustomerFoodCompanyDetailsContracts.IPresenter {

    private lateinit var obj: CustomerFoodCompanyDetailsResponseHandler

    override fun getCompanyData(companyID: String) {
        val pd = MainUtils.getLoadingDialog(context, "Please Wait", "Loading company data", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/allcompany.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                try {

                    val jsonArr = JSONArray(response)
                    var count = (jsonArr.length() - 1)
                    while (count >= 0) {
                        val json = jsonArr.getJSONObject(count)
                        if (json.getString("id").equals(companyID)) {
                            obj = Gson().fromJson(
                                json.toString(),
                                CustomerFoodCompanyDetailsResponseHandler::class.java
                            )
                            mView.onGetCompanyDataResult(false, obj)
                            break
                        }
                        count--
                    }


                } catch (exc: Exception) {
                    mView.onGetCompanyDataResult(true, null)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onGetCompanyDataResult(true, null)
            })
        requestQueue.add(stringRequest)
    }

}