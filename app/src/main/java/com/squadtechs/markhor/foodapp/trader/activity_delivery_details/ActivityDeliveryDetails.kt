package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_company_timings.ActivityCompanyTimings
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.ActivityTraderMain
import com.xw.repo.BubbleSeekBar

class ActivityDeliveryDetails : AppCompatActivity(), DeliveryDetailsContracts.IView {

    private lateinit var txtDeliverPositive: TextView
    private lateinit var txtDeliverNegative: TextView
    private lateinit var linearback: LinearLayout
    private lateinit var btnNext: Button
    private lateinit var edtPickUpInformation: EditText
    private lateinit var edtDeliveryTime: EditText
    private lateinit var txtDeliveryTime: TextView
    private lateinit var bubbleSeekBar: BubbleSeekBar
    private lateinit var mPresenter: DeliveryDetailsContracts.IPresenter
    private var deliver: Boolean = true
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

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
            txtDeliveryTime.visibility = View.GONE
            edtDeliveryTime.visibility = View.GONE
        }

        txtDeliverPositive.setOnClickListener {
            txtDeliverPositive.setBackgroundResource(R.drawable.txt_back_selected)
            txtDeliverNegative.setBackgroundResource(R.drawable.edit_text_background)
            deliver = true
            txtDeliveryTime.visibility = View.VISIBLE
            edtDeliveryTime.visibility = View.VISIBLE
        }

        btnNext.setOnClickListener {
            val range = bubbleSeekBar.progress.toString()
            val pickupInformation = edtPickUpInformation.text.toString().trim()
            mPresenter.initValidation(
                deliver,
                range,
                pickupInformation,
                edtDeliveryTime.text.toString()
            )
        }

        linearback.setOnClickListener {
            startActivity(Intent(this, ActivityCompanyTimings::class.java))
            finish()
        }

    }

    private fun initViews() {
        txtDeliveryTime = findViewById(R.id.txt_time_title)
        txtDeliverPositive = findViewById(R.id.txt_deliver_yes)
        txtDeliverNegative = findViewById(R.id.txt_deliver_no)
        linearback = findViewById(R.id.linear_go_back)
        btnNext = findViewById(R.id.btn_trader_next)
        mPresenter = DeliveryDetailsPresenter(this@ActivityDeliveryDetails, this)
        bubbleSeekBar = findViewById(R.id.delivery_range)
        edtPickUpInformation = findViewById(R.id.edt_pick_up_information)
        edtDeliveryTime = findViewById(R.id.edt_delivery_time)
        pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.addDeliveryDetails()
        } else {
            Toast.makeText(this, "Delivery info must be filled", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAddDeliveryDetailsResult(status: Boolean) {
        if (status) {
            editor.putString("current_screen", "complete")
            editor.apply()
            Toast.makeText(this, "Profile completed", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ActivityTraderMain::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        editor.putString("current_screen", "trader delivery details")
        editor.apply()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCompanyTimings::class.java))
        finish()
    }

}
