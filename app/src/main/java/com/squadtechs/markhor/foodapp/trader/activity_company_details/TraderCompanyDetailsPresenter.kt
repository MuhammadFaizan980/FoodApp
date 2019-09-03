package com.squadtechs.markhor.foodapp.trader.activity_company_details

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap

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
            Response.Listener { response ->
                Log.i("m_response", response)
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val trader_id: String =
                    context.getSharedPreferences("trader_credentials", Context.MODE_PRIVATE)
                        .getString("trader_id", "n/a") as String

                val company_type: String =
                    context.getSharedPreferences("trader_credentials", Context.MODE_PRIVATE)
                        .getString("trader_category", "n/a") as String

                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArr = stream.toByteArray()
                val imageString =
                    "data:image/png;base64,${Base64.encodeToString(byteArr, Base64.DEFAULT)}"

                Log.i("image_string", imageString)

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
        requestQueue.add(stringRequest)
    }
}