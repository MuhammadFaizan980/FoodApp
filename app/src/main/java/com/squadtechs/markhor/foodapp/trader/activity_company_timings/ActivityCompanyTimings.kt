package com.squadtechs.markhor.foodapp.trader.activity_company_timings

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
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
    private lateinit var coordinates: String

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
    }

    private fun initViews() {
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
        //TODO: not implemented
    }

    override fun onAddCompanyTimingsResult(status: Boolean) {
        //TODO: not implemented
    }

    override fun onLocationResult(city: String, coordinates: String) {
        //TODO: not implemented
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

}
