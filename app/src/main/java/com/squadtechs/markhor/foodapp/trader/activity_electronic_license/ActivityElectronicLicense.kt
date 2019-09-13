package com.squadtechs.markhor.foodapp.trader.activity_electronic_license

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.ActivityThankyou
import com.squadtechs.markhor.foodapp.trader.activity_delivery_details.ActivityDeliveryDetails

class ActivityElectronicLicense : AppCompatActivity(), View.OnClickListener,
    ElectronicLicenseContracts.IView {

    private lateinit var imgLicenseFirst: ImageView
    private lateinit var imgLicenseSecond: ImageView
    private lateinit var imgLicenseThird: ImageView
    private lateinit var btnDone: Button
    private lateinit var mPresenter: ElectronicLicenseContracts.IPresenter
    private lateinit var linearBack: LinearLayout
    private var uri1: Uri? = null
    private var uri2: Uri? = null
    private var uri3: Uri? = null

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
        linearBack.setOnClickListener(this)
    }

    private fun iniViews() {
        imgLicenseFirst = findViewById(R.id.img_licence_first)
        imgLicenseSecond = findViewById(R.id.img_licence_second)
        imgLicenseThird = findViewById(R.id.img_licence_third)
        btnDone = findViewById(R.id.btn_done)
        mPresenter =
            ElectronicLicensePresenter(
                this@ActivityElectronicLicense,
                this
            )
        linearBack = findViewById(R.id.linear_go_back)
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
                mPresenter.initValidation(uri1, uri2, uri3)
            }
            R.id.linear_go_back -> {
                startActivity(Intent(this, ActivityDeliveryDetails::class.java))
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 12 && resultCode == RESULT_OK && data != null) {
            uri1 = data.data!!
            imgLicenseFirst.setImageURI(uri1)
        } else if (requestCode == 13 && resultCode == Activity.RESULT_OK && data != null) {
            uri2 = data.data!!
            imgLicenseSecond.setImageURI(uri2)
        } else if (requestCode == 14 && resultCode == Activity.RESULT_OK && data != null) {
            uri3 = data.data!!
            imgLicenseThird.setImageURI(uri3)
        }
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.saveLicense()
        } else {
            Toast.makeText(this, "Please select all license files first", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveLicenseResult(status: Boolean) {
        if (status) {
            startActivity(Intent(this@ActivityElectronicLicense, ActivityThankyou::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityDeliveryDetails::class.java))
        finish()
    }

}
