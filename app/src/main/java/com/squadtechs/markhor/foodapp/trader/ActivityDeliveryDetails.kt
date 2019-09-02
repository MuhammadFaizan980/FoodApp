package com.squadtechs.markhor.foodapp.trader

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityDeliveryDetails : AppCompatActivity() {

    private lateinit var txtDeliverPositive: TextView
    private lateinit var txtDeliverNegative: TextView
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)
        initViews()
        setListeners()
    }

    private fun initViews() {
        txtDeliverPositive = findViewById(R.id.txt_deliver_yes)
        txtDeliverNegative = findViewById(R.id.txt_deliver_no)
        btnNext = findViewById(R.id.btn_trader_next)
    }

    private fun setListeners() {


        txtDeliverPositive.setOnClickListener {
            txtDeliverPositive.setBackgroundColor(Color.parseColor("#4c4c4c"))
            txtDeliverNegative.setBackgroundColor(Color.parseColor("#FBFBFB"))
        }
        txtDeliverNegative.setOnClickListener {
            txtDeliverNegative.setBackgroundColor(Color.parseColor("#4c4c4c"))
            txtDeliverPositive.setBackgroundColor(Color.parseColor("#FBFBFB"))
        }

        btnNext.setOnClickListener {
            val progressDialgo = ProgressDialog(this)
            progressDialgo.setCancelable(false)
            progressDialgo.setTitle("Please Wait!")
            progressDialgo.setMessage("Uploading delivery details")
            progressDialgo.show()
            object : CountDownTimer(5000, 100) {
                override fun onFinish() {
                    progressDialgo.cancel()
                    val dialog = AlertDialog.Builder(this@ActivityDeliveryDetails)
                    dialog.setTitle("Info!")
                    dialog.setMessage("Your food details have been saved")
                    dialog.setCancelable(false)
                    dialog.setNegativeButton("Next") { dialogInterface, i ->
                        startActivity(Intent(this@ActivityDeliveryDetails, ActivityElectronicLicense::class.java))
                        finish()
                    }
                    dialog.show()
                }

                override fun onTick(p0: Long) {
                }
            }.start()
        }
    }

}
