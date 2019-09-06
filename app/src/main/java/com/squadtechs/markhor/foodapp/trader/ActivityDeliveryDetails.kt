package com.squadtechs.markhor.foodapp.trader

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_company_timings.ActivityCompanyTimings

class ActivityDeliveryDetails : AppCompatActivity() {

    private lateinit var txtDeliverPositive: TextView
    private lateinit var txtDeliverNegative: TextView
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)
        initViews()
    }

    private fun initViews() {
        txtDeliverPositive = findViewById(R.id.txt_deliver_yes)
        txtDeliverNegative = findViewById(R.id.txt_deliver_no)
        btnNext = findViewById(R.id.btn_trader_next)
    }

    override fun onStart() {
        super.onStart()
        val pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("current_screen", "trader delivery details")
        editor.apply()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCompanyTimings::class.java))
        finish()
    }


}
