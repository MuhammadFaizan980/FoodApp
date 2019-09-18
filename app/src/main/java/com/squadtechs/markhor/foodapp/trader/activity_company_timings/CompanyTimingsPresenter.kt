package com.squadtechs.markhor.foodapp.trader.activity_company_timings

import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject


class CompanyTimingsPresenter constructor(
    private val mView: CompanyTimingsContracts.IView,
    private val context: Context
) : CompanyTimingsContracts.IPresenter {

    override fun initTimePicker(key: String) {
        val timePickerDialog = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
                mView.onTimePickerResult("$hourOfDay:$minutes", key)
            }, 0, 0, false
        )
        timePickerDialog.show()
    }

    private val API = "http://squadtechsolution.com/android/v1/company_Timing.php"
    private lateinit var mModel: CompanyTimingsContracts.IModel
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
    private var location: String? = null

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
        saturdayClose: String,
        location: String?
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
        this.location = location
        mModel = CompanyTimingsModel(
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
            saturdayClose,
            location
        )
        mView.onValidationResult(mModel.validate())
    }

    override fun addCompanyTimings() {
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
                    if (json.getString("status").equals("Timing Update SuccuessFull")) {
                        mView.onAddCompanyTimingsResult(true)
                    } else {
                        mView.onAddCompanyTimingsResult(false)
                    }
                } catch (exc: Exception) {
                    mView.onAddCompanyTimingsResult(false)
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                mView.onAddCompanyTimingsResult(false)
                Log.i("resp", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                val company_id = context.getSharedPreferences("company_data", Context.MODE_PRIVATE)
                    .getString("company_id", "n/a") as String
                map["Monday"] = "$mondayStart, $mondayEnd"
                map["Tuesday"] = "$tuesdayStart, $tuesdayEnd"
                map["Wednesday"] = "$wednesdayStart, $wednesdayEnd"
                map["Thursday"] = "$thursdayStart, $thursdayEnd"
                map["Friday"] = "$fridayStart, $fridayEnd"
                map["Saturday"] = "$saturdayStart, $saturdayEnd"
                map["Sunday"] = "$sundayStart, $sundayEnd"
                map["company_id"] = company_id
                map["address"] = location!!
                Log.i("timings", map.toString())
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

}