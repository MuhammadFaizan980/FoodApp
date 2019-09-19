package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_timings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type.ActivityTraderEditDeliveryRangeType
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.ActivityTraderMain

class ActivityTraderEditCompanyTimings : AppCompatActivity(), EditTimingContracts.IView {

    private lateinit var mPresenter: EditTimingContracts.IPresenter
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
    private lateinit var openView: View
    private lateinit var closeView: View
    private lateinit var btnSave: Button
    private lateinit var btnGoBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(+R.layout.activity_trader_edit_company_timings)
        initViews()
        setListeners()
    }

    private fun setListeners() {

        openView.setOnClickListener {
            mPresenter.initTimePicker("sunday_start")
        }

        closeView.setOnClickListener {
            mPresenter.initTimePicker("sunday_end")
        }

        btnSave.setOnClickListener {
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
                saturdayClose
            )
        }

        btnGoBack.setOnClickListener {
            startActivity(Intent(this, ActivityTraderEditDeliveryRangeType::class.java))
            finish()
        }

    }


    private fun initViews() {
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
        btnSave = findViewById(R.id.btn_save_changes)
        openView = findViewById(R.id.open_time_vew)
        closeView = findViewById(R.id.close_time_vew)
        btnGoBack = findViewById(R.id.img_go_back)
        mPresenter = EditTimingPresenter(this@ActivityTraderEditCompanyTimings, this)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            //TODO: call editCompanyTimings() on presenter
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onEditCompanyTimingsResult(status: Boolean) {
        if (status) {
            mPresenter.editCompanyInformation(
                intent!!.extras!!.get("company_name") as String,
                intent!!.extras!!.get("company_description") as String,
                intent!!.extras!!.get("company_cuisine") as String,
                intent!!.extras!!.get("company_phone") as String,
                intent!!.extras!!.get("company_delivery_time") as String,
                intent!!.extras!!.get("company_delivery_range") as String,
                intent!!.extras!!.get("company_pick_up_info") as String,
                intent!!.extras!!.get("company_coordinates") as String,
                intent!!.extras!!.get("company_delivery_type") as String,
                intent!!.extras!!.get("company_logo_uri") as String
            )
        } else {
            Toast.makeText(this, "There was an error updating timings", Toast.LENGTH_LONG).show()
        }
    }

    override fun onEditCompanyInformationResult(status: Boolean) {
        if (status) {
            startActivity(Intent(this, ActivityTraderMain::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error updating company profile", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onTimePickerResult(timeValue: String, key: String) {
        if (key.equals("sunday_start")) {
            edtSundayStart.setText(timeValue)
            edtMondayStart.setText(timeValue)
            edtTuesdayStart.setText(timeValue)
            edtWednesdayStart.setText(timeValue)
            edtThursdayStart.setText(timeValue)
            edtFridayStart.setText(timeValue)
            edtSaturdayStart.setText(timeValue)
        } else {
            edtSundayEnd.setText(timeValue)
            edtMondayEnd.setText(timeValue)
            edtTuesdayEnd.setText(timeValue)
            edtWednesdayEnd.setText(timeValue)
            edtThursdayEnd.setText(timeValue)
            edtFridayEnd.setText(timeValue)
            edtSaturdayEnd.setText(timeValue)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityTraderEditDeliveryRangeType::class.java))
        finish()
    }

}
