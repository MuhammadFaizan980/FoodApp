package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_logo.ActivityTraderEditCompanyLogo
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_timings.ActivityTraderEditCompanyTimings
import com.xw.repo.BubbleSeekBar

class ActivityTraderEditDeliveryRangeType : AppCompatActivity(), EditDeliveryRangeContracts.IView {

    private lateinit var txtDeliverYes: TextView
    private lateinit var txtDeliverNo: TextView
    private lateinit var txtTimeTitle: TextView
    private lateinit var rangeSlider: BubbleSeekBar
    private lateinit var edtDeliveryTime: EditText
    private lateinit var edtPickUpInfo: EditText
    private lateinit var btnNext: Button
    private lateinit var imgGoBack: ImageView
    private var doYouDeliver: String = "no"
    private lateinit var deliveryTime: String
    private lateinit var deliveryRange: String
    private lateinit var pickUpInfo: String
    private lateinit var mPresenter: EditDeliveryRangeContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_delivery_range_type)
        initViews()
        setListeners()
    }

    private fun setListeners() {

        btnNext.setOnClickListener {
            deliveryTime = edtDeliveryTime.text.toString().trim()
            deliveryRange = rangeSlider.progress.toString()
            pickUpInfo = edtPickUpInfo.text.toString().trim()
            mPresenter.initValidation(doYouDeliver, deliveryTime, deliveryRange, pickUpInfo)
        }

        txtDeliverYes.setOnClickListener {
            doYouDeliver = "yes"
            edtDeliveryTime.visibility = View.VISIBLE
            txtTimeTitle.visibility = View.VISIBLE
            txtDeliverYes.setBackgroundResource(R.drawable.txt_edit_range_selected)
            txtDeliverNo.setBackgroundResource(R.drawable.txt_edit_range_unselected)
        }
        txtDeliverNo.setOnClickListener {
            doYouDeliver = "no"
            edtDeliveryTime.visibility = View.GONE
            txtTimeTitle.visibility = View.GONE
            txtDeliverYes.setBackgroundResource(R.drawable.txt_edit_range_unselected)
            txtDeliverNo.setBackgroundResource(R.drawable.txt_edit_range_selected)
        }

        imgGoBack.setOnClickListener {
            startActivity(Intent(this, ActivityTraderEditCompanyLogo::class.java))
            finish()
        }

    }

    private fun initViews() {
        txtDeliverYes = findViewById(R.id.txt_deliver_yes)
        txtDeliverNo = findViewById(R.id.txt_deliver_no)
        edtDeliveryTime = findViewById(R.id.edt_delivery_time)
        rangeSlider = findViewById(R.id.delivery_range)
        edtPickUpInfo = findViewById(R.id.edt_pick_up_information)
        btnNext = findViewById(R.id.btn_trader_edit_company_next)
        imgGoBack = findViewById(R.id.img_go_back)
        txtTimeTitle = findViewById(R.id.txt_time_title)
        mPresenter = EditDeliveryRangePresenter(this@ActivityTraderEditDeliveryRangeType)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            val mIntent = Intent(this, ActivityTraderEditCompanyTimings::class.java)


            mIntent.putExtra("company_name", intent!!.extras!!.get("company_name") as String)
            mIntent.putExtra("company_phone", intent!!.extras!!.get("company_phone") as String)
            mIntent.putExtra(
                "company_description",
                intent!!.extras!!.get("company_description") as String
            )
            mIntent.putExtra("company_cuisine", intent!!.extras!!.get("company_cuisine") as String)
//            mIntent.putExtra(
//                "company_logo",
//                intent!!.extras!!.get("company_logo") as String
//            )
            mIntent.putExtra(
                "company_coordinates",
                intent!!.extras!!.get("company_coordinates") as String
            )
            mIntent.putExtra("company_delivery_type", doYouDeliver)
            mIntent.putExtra("company_delivery_range", deliveryRange)
            mIntent.putExtra("company_pick_up_info", pickUpInfo)
            mIntent.putExtra("company_delivery_time", deliveryTime)
            startActivity(mIntent)
            finish()
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityTraderEditCompanyLogo::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        edtDeliveryTime.visibility = View.GONE
        txtTimeTitle.visibility = View.GONE
    }

}
