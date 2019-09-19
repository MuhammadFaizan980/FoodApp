package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.xw.repo.BubbleSeekBar

class ActivityTraderEditDeliveryRangeType : AppCompatActivity() {

    private lateinit var txtDeliverYes: TextView
    private lateinit var txtDeliverNo: TextView
    private lateinit var txtTimeTitle: TextView
    private lateinit var rangeSLider: BubbleSeekBar
    private lateinit var edtTime: EditText
    private lateinit var edtPickUpInfo: EditText
    private lateinit var btnNext: Button
    private lateinit var imgGoBack: ImageView
    private var deliver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_delivery_range_type)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        txtDeliverYes.setOnClickListener {
            deliver = true
            edtTime.visibility = View.VISIBLE
            txtTimeTitle.visibility = View.VISIBLE
            txtDeliverYes.setBackgroundResource(R.drawable.txt_edit_range_selected)
            txtDeliverNo.setBackgroundResource(R.drawable.txt_edit_range_unselected)
        }
        txtDeliverNo.setOnClickListener {
            deliver = false
            edtTime.visibility = View.GONE
            txtTimeTitle.visibility = View.GONE
            txtDeliverYes.setBackgroundResource(R.drawable.txt_edit_range_unselected)
            txtDeliverNo.setBackgroundResource(R.drawable.txt_edit_range_selected)
        }
    }

    private fun initViews() {
        txtDeliverYes = findViewById(R.id.txt_deliver_yes)
        txtDeliverNo = findViewById(R.id.txt_deliver_no)
        edtTime = findViewById(R.id.edt_delivery_time)
        rangeSLider = findViewById(R.id.delivery_range)
        edtPickUpInfo = findViewById(R.id.edt_pick_up_information)
        btnNext = findViewById(R.id.btn_trader_edit_company_next)
        imgGoBack = findViewById(R.id.img_go_back)
        txtTimeTitle = findViewById(R.id.txt_time_title)
    }

    override fun onStart() {
        super.onStart()
        edtTime.visibility = View.GONE
        txtTimeTitle.visibility = View.GONE
    }

}
