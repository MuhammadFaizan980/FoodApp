package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
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
            //TODO: take user to next screen
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        edtDeliveryTime.visibility = View.GONE
        txtTimeTitle.visibility = View.GONE
    }

}
