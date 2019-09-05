package com.squadtechs.markhor.foodapp.trader

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityCompanyTimings : AppCompatActivity() {

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
    private lateinit var linearback: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_timings)
        initViews()
        setListeners()
    }

    private fun setListeners() {
    }

    private fun initViews() {
        linearback = findViewById(R.id.linear_go_back)
        btnNext = findViewById(R.id.btn_trader_next)
    }

}
