package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_company_timings.ActivityCompanyTimings

class ActivityDeliveryDetails : AppCompatActivity() {

    private lateinit var txtDeliverPositive: TextView
    private lateinit var txtDeliverNegative: TextView
    private lateinit var linearback: LinearLayout
    private lateinit var btnNext: Button
    private var deliver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        txtDeliverNegative.setOnClickListener {
            txtDeliverPositive.setBackgroundResource(R.drawable.edit_text_background)
            txtDeliverNegative.setBackgroundResource(R.drawable.txt_back_selected)
            deliver = false
        }

        txtDeliverPositive.setOnClickListener {
            txtDeliverPositive.setBackgroundResource(R.drawable.txt_back_selected)
            txtDeliverNegative.setBackgroundResource(R.drawable.edit_text_background)
            deliver = true
        }
    }

    private fun initViews() {
        txtDeliverPositive = findViewById(R.id.txt_deliver_yes)
        txtDeliverNegative = findViewById(R.id.txt_deliver_no)
        linearback = findViewById(R.id.linear_go_back)
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
