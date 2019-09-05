package com.squadtechs.markhor.foodapp.trader.activity_company_details

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.ByteArrayOutputStream


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
        val pd = ProgressDialog(context)
        pd.setTitle("Please Wait")
        pd.setMessage("Adding company")
        pd.setCancelable(false)
        pd.show()
        val API = "http://squadtechsolution.com//android/v1/compnay_profile.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                val json = JSONObject(response)
                if (json.getString("status").equals("update_success")) {
                    mView.onAddCompanyDetailsResult(true)
                } else {
                    mView.onAddCompanyDetailsResult(false)
                }
                Log.i("m_response", response)
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onAddCompanyDetailsResult(false)
                Log.i("m_response", error.toString())
            }) {

            override fun getParams(): MutableMap<String, String> {
                val trader_id: String =
                    context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                        .getString("trader_id", "n/a") as String

                val company_type: String =
                    context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                        .getString("company_type", "n/a") as String

                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream)
                val byteArr = stream.toByteArray()
                val imageString =
                    "data:image/png;base64,${Base64.encodeToString(byteArr, Base64.DEFAULT)}"

                val map = HashMap<String, String>()
                map["company_name"] = companyName
                map["cuisine"] = cuisine
                map["company_description"] = companyDescription
                map["trader_id"] = trader_id
                map["company_type"] = company_type
                map["logo_img"] = imageString
                return map
            }
        }
        stringRequest.retryPolicy = DefaultRetryPolicy(
            10000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue.add(stringRequest)
    }
}