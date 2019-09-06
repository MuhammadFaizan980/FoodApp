package com.squadtechs.markhor.foodapp.trader.activity_company_timings

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.ActivityDeliveryDetails
import com.squadtechs.markhor.foodapp.trader.activity_company_details.ActivityCompanyDetails
import com.squadtechs.markhor.foodapp.trader.activity_pick_location.ActivityPickLocation

class ActivityCompanyTimings : AppCompatActivity(), CompanyTimingsContracts.IView {

    private lateinit var edtSundayStart: EditText
    private lateinit var edtSundayEnd: EditText
    private lateinit var edtMondayStart: EditText
    private lateinit var edtMondayEnd: EditText
    private lateinit var edtTuesdayStart: EditText
    private lateinit var edtTuesdayEnd: EditText
    private lateinit var edtWednesdayStart: EditText
    private lateinit var edtWednesdayEnd: EditText
    private lateinit var edtThursdayStart: EditText
    private lateinit var edtThursdayEnd: EditText
    private lateinit var edtFridayStart: EditText
    private lateinit var edtFridayEnd: EditText
    private lateinit var edtSaturdayStart: EditText
    private lateinit var edtSaturdayEnd: EditText
    private lateinit var btnNext: Button
    private lateinit var imgLocation: ImageView
    private lateinit var linearback: LinearLayout
    private var coordinates: String? = null
    private lateinit var mPresenter: CompanyTimingsContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_timings)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        imgLocation.setOnClickListener {
            startActivityForResult(
                Intent(
                    this@ActivityCompanyTimings,
                    ActivityPickLocation::class.java
                ), 120
            )
        }
        btnNext.setOnClickListener {
            val sundayOpen = edtSundayStart.text.toString().trim()
            val sundayClose = edtSundayEnd.text.toString().trim()
            val mondayOpen = edtMondayStart.text.toString().trim()
            val mondayClose = edtMondayEnd.text.toString().trim()
            val tuesdayOpen = edtTuesdayStart.text.toString().trim()
            val tuesdayClose = edtTuesdayEnd.text.toString().trim()
            val wednesdayOpen = edtWednesdayStart.text.toString().trim()
            val wednesdayClose = edtWednesdayEnd.text.toString().trim()
            val thursdayOpen = edtThursdayStart.text.toString().trim()
            val thursdayClose = edtThursdayEnd.text.toString().trim()
            val fridayOpen = edtFridayStart.text.toString().trim()
            val fridayClose = edtFridayEnd.text.toString().trim()
            val saturdayOpen = edtSaturdayStart.text.toString().trim()
            val saturdayClose = edtSaturdayEnd.text.toString().trim()
            mPresenter.initValidation(
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
                coordinates
            )
        }

        linearback.setOnClickListener {
            startActivity(Intent(this, ActivityCompanyDetails::class.java))
            finish()
        }

    }

    private fun initViews() {
        mPresenter = CompanyTimingsPresenter(this@ActivityCompanyTimings, this)
        imgLocation = findViewById(R.id.img_company_location)
        edtSundayStart = findViewById(R.id.edt_open_sunday)
        edtSundayEnd = findViewById(R.id.edt_close_sunday)
        edtMondayStart = findViewById(R.id.edt_open_monday)
        edtMondayEnd = findViewById(R.id.edt_close_monday)
        edtTuesdayStart = findViewById(R.id.edt_open_tuesday)
        edtTuesdayEnd = findViewById(R.id.edt_close_tuesday)
        edtWednesdayStart = findViewById(R.id.edt_open_wednesday)
        edtWednesdayEnd = findViewById(R.id.edt_close_wednesday)
        edtThursdayStart = findViewById(R.id.edt_open_thursday)
        edtThursdayEnd = findViewById(R.id.edt_close_thursday)
        edtFridayStart = findViewById(R.id.edt_open_friday)
        edtFridayEnd = findViewById(R.id.edt_close_friday)
        edtSaturdayStart = findViewById(R.id.edt_open_saturday)
        edtSaturdayEnd = findViewById(R.id.edt_close_saturday)
        linearback = findViewById(R.id.linear_go_back)
        btnNext = findViewById(R.id.btn_trader_next)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.addCompanyTimings()
        } else {
            Toast.makeText(this, "Invalid data entry", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAddCompanyTimingsResult(status: Boolean) {
        if (status) {
            startActivity(Intent(this, ActivityDeliveryDetails::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 120 && resultCode == Activity.RESULT_OK) {
            coordinates = getSharedPreferences("coordinates", Context.MODE_PRIVATE).getString(
                "coordinates",
                "n/a"
            ) as String
            imgLocation.setImageResource(R.drawable.marker_pin)
        }
    }

    override fun onStart() {
        super.onStart()
        val pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("current_screen", "trader company timings")
        editor.apply()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCompanyDetails::class.java))
        finish()
    }

}
