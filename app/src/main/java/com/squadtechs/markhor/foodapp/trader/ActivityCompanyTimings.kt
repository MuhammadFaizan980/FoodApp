package com.squadtechs.markhor.foodapp.trader

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityCompanyTimings : AppCompatActivity() {

    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_timings)
        initViews()
        setListeners()
    }

    private fun setListeners() {

        btnNext.setOnClickListener {
            val progressDialgo = ProgressDialog(this)
            progressDialgo.setCancelable(false)
            progressDialgo.setTitle("Please Wait!")
            progressDialgo.setMessage("Uploading timings")
            progressDialgo.show()
            object : CountDownTimer(5000, 100) {
                override fun onFinish() {
                    progressDialgo.cancel()
                    val dialog = AlertDialog.Builder(this@ActivityCompanyTimings)
                    dialog.setTitle("Info!")
                    dialog.setMessage("Company timings added successfully\nYou need to add delivery details next")
                    dialog.setCancelable(false)
                    dialog.setNegativeButton("Next") { dialogInterface, i ->
                        startActivity(Intent(this@ActivityCompanyTimings, ActivityDeliveryDetails::class.java))
                        finish()
                    }
                    dialog.show()
                }

                override fun onTick(p0: Long) {
                }
            }.start()
        }
    }

    private fun initViews() {
        btnNext = findViewById(R.id.btn_trader_next)
    }

}
