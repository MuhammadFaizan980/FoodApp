package com.squadtechs.markhor.foodapp.activity_electronic_license

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_delivery_details.ActivityDeliveryDetails
import com.squadtechs.markhor.foodapp.trader.trader_login.ActivityTraderLogin

class ActivityElectronicLicense : AppCompatActivity(), View.OnClickListener {

    private lateinit var imgLicenseFirst: ImageView
    private lateinit var imgLicenseSecond: ImageView
    private lateinit var imgLicenseThird: ImageView
    private lateinit var btnDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electronic_license)
        iniViews()
        setListeners()
    }

    private fun setListeners() {
        imgLicenseFirst.setOnClickListener(this)
        imgLicenseSecond.setOnClickListener(this)
        imgLicenseThird.setOnClickListener(this)
        btnDone.setOnClickListener(this)
    }

    private fun iniViews() {
        imgLicenseFirst = findViewById(R.id.img_licence_first)
        imgLicenseSecond = findViewById(R.id.img_licence_second)
        imgLicenseThird = findViewById(R.id.img_licence_third)
        btnDone = findViewById(R.id.btn_done)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.img_licence_first -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 12)
            }
            R.id.img_licence_second -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 13)
            }
            R.id.img_licence_third -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 14)
            }
            R.id.btn_done -> {
                val progressDialgo = ProgressDialog(this)
                progressDialgo.setCancelable(false)
                progressDialgo.setTitle("Please Wait!")
                progressDialgo.setMessage("Uploading licence")
                progressDialgo.show()
                object : CountDownTimer(5000, 100) {
                    override fun onFinish() {
                        progressDialgo.cancel()
                        val dialog = AlertDialog.Builder(this@ActivityElectronicLicense)
                        dialog.setTitle("Info!")
                        dialog.setMessage("Your license has been uploaded and your account is pending for approval by admin\nYou will be able to login once your account is approved")
                        dialog.setCancelable(false)
                        dialog.setNegativeButton("Close") { dialogInterface, i ->
                            startActivity(
                                Intent(
                                    this@ActivityElectronicLicense,
                                    ActivityTraderLogin::class.java
                                )
                            )
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 12 && resultCode == RESULT_OK && data != null) {
            val uri = data.data!!
            imgLicenseFirst.setImageURI(uri)
        } else if (requestCode == 13 && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data!!
            imgLicenseSecond.setImageURI(uri)
        } else if (requestCode == 14 && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data!!
            imgLicenseThird.setImageURI(uri)
        }
    }

    override fun onStart() {
        super.onStart()
        val pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("current_screen", "trader license")
        editor.apply()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityDeliveryDetails::class.java))
        finish()
    }

}
