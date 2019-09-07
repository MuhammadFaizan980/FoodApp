package com.squadtechs.markhor.foodapp.trader.activity_electronic_license

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


class ElectronicLicensePresenter(
    private val mView: ElectronicLicenseContracts.IView,
    private val context: Context
) : ElectronicLicenseContracts.IPresenter {

    private val API = "http://squadtechsolution.com/android/v1/traderlicense.php"
    private lateinit var mModel: ElectronicLicenseContracts.IModel
    private lateinit var uri1: Uri
    private lateinit var uri2: Uri
    private lateinit var uri3: Uri

    override fun initValidation(uri1: Uri?, uri2: Uri?, uri3: Uri?) {
        mModel =
            ElectronicLicenseModel(
                uri1,
                uri2,
                uri3
            )
        if (mModel.validate()) {
            mView.onValidationResult(true)
            this.uri1 = uri1!!
            this.uri2 = uri2!!
            this.uri3 = uri3!!
        } else {
            mView.onValidationResult(false)
        }
    }

    override fun saveLicense() {

        val pd = ProgressDialog(context)
        pd.setTitle("Please Wait")
        pd.setMessage("Adding License")
        pd.setCancelable(false)
        pd.show()

        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                val json = JSONObject(response)
                try {
                    if (json.getString("status").equals("License Uploaded")) {
                        mView.onSaveLicenseResult(true)
                    } else {
                        mView.onSaveLicenseResult(false)
                    }
                } catch (exc: Exception) {
                    mView.onSaveLicenseResult(false)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onSaveLicenseResult(false)
                Log.i("m_resp", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                val trader_id =
                    context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                        .getString("trader_id", "n/a") as String
                map["id"] = trader_id
                map["traderlicense1_image"] = getImageString(uri1)
                map["traderlicense2_image"] = getImageString(uri2)
                map["traderlicense3_image"] = getImageString(uri3)
                map["profile_complete"] = "yes"
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

    private fun getImageString(uri: Uri): String {
        val stream = ByteArrayOutputStream()
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream)
        val arr = stream.toByteArray()
        return "data:image/png;base64,${Base64.encodeToString(arr, Base64.DEFAULT)}"
    }

}