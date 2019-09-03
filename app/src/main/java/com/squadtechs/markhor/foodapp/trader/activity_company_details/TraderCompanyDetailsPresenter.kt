package com.squadtechs.markhor.foodapp.trader.activity_company_details

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class TraderCompanyDetailsPresenter(
    private val mView: TraderCompanyDetailsContracts.IView,
    private val context: Context
) : TraderCompanyDetailsContracts.IPresenter {

    private lateinit var mModel: TraderCompanyDetailsContracts.IModel
    private lateinit var companyName: String
    private lateinit var companyDescription: String
    private lateinit var cuisine: String
    private var uri: Uri? = null

    override fun initValidation(
        companyName: String,
        cuisine: String,
        companyDescription: String,
        uri: Uri?
    ) {
        mModel = TraderCompanyDetailsModel(companyName, cuisine, companyDescription, uri)
        mView.onValidationResult(mModel.validate())
        if (mModel.validate()) {
            this.companyName = companyName
            this.companyDescription = companyDescription
            this.cuisine = cuisine
            this.uri = uri
        }
    }

    override fun addCompanyDetails() {
        val API = "http://squadtechsolution.com//android/v1/compnay_profile.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response -> },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
            }) {}
        requestQueue.add(stringRequest)
    }
}