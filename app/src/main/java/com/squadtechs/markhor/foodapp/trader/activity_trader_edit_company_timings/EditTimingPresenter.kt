package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_timings

import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

class EditTimingPresenter(
    private val mView: EditTimingContracts.IView,
    private val context: Context
) : EditTimingContracts.IPresenter {

    private lateinit var mModel: EditTimingContracts.IModel
    private lateinit var companyID: String
    private lateinit var companyType: String

    private lateinit var sundayStart: String
    private lateinit var sundayEnd: String
    private lateinit var mondayStart: String
    private lateinit var mondayEnd: String
    private lateinit var tuesdayStart: String
    private lateinit var tuesdayEnd: String
    private lateinit var wednesdayStart: String
    private lateinit var wednesdayEnd: String
    private lateinit var thursdayStart: String
    private lateinit var thursdayEnd: String
    private lateinit var fridayStart: String
    private lateinit var fridayEnd: String
    private lateinit var saturdayStart: String
    private lateinit var saturdayEnd: String

    override fun initValidation(
        sundayOpen: String,
        sundayClose: String,
        mondayOpen: String,
        mondayClose: String,
        tuesdayOpen: String,
        tuesdayClose: String,
        wednesdayOpen: String,
        wednesdayClose: String,
        thursdayOpen: String,
        thursdayClose: String,
        fridayOpen: String,
        fridayClose: String,
        saturdayOpen: String,
        saturdayClose: String
    ) {
        sundayStart = sundayOpen
        sundayEnd = sundayClose
        mondayStart = mondayOpen
        mondayEnd = mondayClose
        tuesdayStart = tuesdayOpen
        tuesdayEnd = tuesdayClose
        wednesdayStart = wednesdayOpen
        wednesdayEnd = wednesdayClose
        thursdayStart = thursdayOpen
        thursdayEnd = thursdayClose
        fridayStart = fridayOpen
        fridayEnd = fridayClose
        saturdayStart = saturdayOpen
        saturdayEnd = saturdayClose
        mModel = EditTimingModel(
            sundayOpen,
            sundayClose,
            mondayOpen,
            mondayClose,
            tuesdayOpen,
            tuesdayClose,
            wednesdayOpen,
            wednesdayClose,
            thursdayOpen,
            thursdayClose,
            fridayOpen,
            fridayClose,
            saturdayOpen,
            saturdayClose
        )
        mView.onValidationResult(mModel.validate())
    }

    override fun editCompanyInformation(
        companyName: String,
        companyDescription: String,
        companyCuisine: String,
        companyPhone: String,
        companyDeliveryTime: String,
        companyDeliveryRange: String,
        companyDeliveryPickUpInfo: String,
        companyCoordinates: String,
        companyDeliveryType: String
    ) {
        Toast.makeText(context, companyDeliveryType, Toast.LENGTH_LONG).show()
        val pd = MainUtils.getLoadingDialog(context, "Updating Profile", "Please wait", false)
        pd.show()

        val API = "http://squadtechsolution.com//android/v1/update_company_profile.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("dxdiag", response)
                val json = JSONObject(response)
                try {
                    if (json.getString("status").equals("update_success")) {
                        mView.onEditCompanyInformationResult(true)
                    } else {
                        mView.onEditCompanyInformationResult(false)
                    }
                } catch (exc: Exception) {
                    mView.onEditCompanyInformationResult(false)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onEditCompanyInformationResult(false)
                Log.i("dxdiag", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                val arr = arrayOfNulls<String>(8)
                arr[0] = "Clothes"
                arr[1] = "Accessories"
                arr[2] = "Skincare"
                arr[3] = "Homeware"
                arr[4] = "Toys"
                arr[5] = "Sheos"
                arr[6] = "Bags"
                arr[7] = "Other"


                if (arr.contains(companyCuisine)) {
                    map["cuisine"] = "n/a"
                    map["company_type"] = companyCuisine
                } else {
                    map["cuisine"] = companyCuisine
                    map["company_type"] = companyType
                }

                map["id"] = "1" //TODO: change id after test
                map["company_name"] = companyName

                map["company_description"] = companyDescription
                map["delivery_type"] = companyDeliveryType
                map["company_phone"] = companyPhone
                map["delivery_pickupinfo"] = companyDeliveryPickUpInfo
                map["logo_img"] = context.getSharedPreferences(
                    "logo_string",
                    Context.MODE_PRIVATE
                ).getString("image_string", "n/a") as String
                map["address"] = companyCoordinates
                if (companyDeliveryType.equals("yes")) {
                    map["delivery_fee"] = companyDeliveryTime
                    map["delivery_range"] = companyDeliveryRange
                } else {
                    map["delivery_fee"] = "n/a"
                    map["delivery_range"] = "n/a"
                }
                Log.i("dxdiag", map.toString())
                return map
            }
        }
        stringRequest.setRetryPolicy(
            DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy. DEFAULT_BACKOFF_MULT))
        requestQueue.add(stringRequest)
    }

    override fun editCompanyTimings() {
        companyID = context.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        ).getString("company_id", "n/a") as String
        companyType = context.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        ).getString("company_type", "n/a") as String

        //http start
        val pd = MainUtils.getLoadingDialog(context, "Updating Timings", "Please wait", false)
        pd.show()

        val API = "http://squadtechsolution.com//android/v1/update_company_timing.php"
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("dxdiag", response)
                val json = JSONObject(response)
                try {
                    if (json.getString("status").equals("update_success")) {
                        mView.onEditCompanyTimingsResult(true)
                    } else {
                        mView.onEditCompanyTimingsResult(false)
                    }
                } catch (exc: Exception) {
                    mView.onEditCompanyTimingsResult(false)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onEditCompanyTimingsResult(false)
                Log.i("dxdiag", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["Monday"] = "$mondayStart,$mondayEnd"
                map["Tuesday"] = "$tuesdayStart,$tuesdayEnd"
                map["Wednesday"] = "$wednesdayStart,$wednesdayEnd"
                map["Thursday"] = "$thursdayStart,$thursdayEnd"
                map["Friday"] = "$fridayStart,$fridayEnd"
                map["Saturday"] = "$saturdayStart,$saturdayEnd"
                map["Sunday"] = "$sundayStart,$sundayEnd"
                map["company_id"] = "1" //TODO: change id after test
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

    override fun initTimePicker(key: String) {
        val timePickerDialog = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
                mView.onTimePickerResult("$hourOfDay:$minutes", key)
            }, 0, 0, false
        )
        timePickerDialog.show()
    }

}