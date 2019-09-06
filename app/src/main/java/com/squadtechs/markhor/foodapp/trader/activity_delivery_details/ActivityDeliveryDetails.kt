package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.ActivityElectronicLicense
import com.squadtechs.markhor.foodapp.trader.activity_company_timings.ActivityCompanyTimings
import com.xw.repo.BubbleSeekBar

class ActivityDeliveryDetails : AppCompatActivity(), DeliveryDetailsContracts.IView {

    private lateinit var txtDeliverPositive: TextView
    private lateinit var txtDeliverNegative: TextView
    private lateinit var linearback: LinearLayout
    private lateinit var btnNext: Button
    private lateinit var edtPickUpInformation: EditText
    private lateinit var bubbleSeekBar: BubbleSeekBar
    private lateinit var mPresenter: DeliveryDetailsContracts.IPresenter
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

        btnNext.setOnClickListener {
            val range = bubbleSeekBar.progress.toString()
            val pickupInformation = edtPickUpInformation.text.toString().trim()
            mPresenter.initValidation(deliver, range, pickupInformation)
        }

    }

    private fun initViews() {
        txtDeliverPositive = findViewById(R.id.txt_deliver_yes)
        txtDeliverNegative = findViewById(R.id.txt_deliver_no)
        linearback = findViewById(R.id.linear_go_back)
        btnNext = findViewById(R.id.btn_trader_next)
        mPresenter = DeliveryDetailsPresenter(this@ActivityDeliveryDetails, this)
        bubbleSeekBar = findViewById(R.id.delivery_range)
        edtPickUpInformation = findViewById(R.id.edt_pick_up_information)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.addDeliveryDetails()
        } else {
            Toast.makeText(this, "Invalid data entry", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAddDeliveryDetailsResult(status: Boolean) {
        if (status) {
            startActivity(Intent(this, ActivityElectronicLicense::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
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
