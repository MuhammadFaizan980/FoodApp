package com.squadtechs.markhor.foodapp.trader.activity_company_details

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.ActivityCompanyType
import com.squadtechs.markhor.foodapp.trader.activity_company_timings.ActivityCompanyTimings
import com.squadtechs.markhor.foodapp.trader.trader_registration.ActivityTraderSignup

class ActivityCompanyDetails : AppCompatActivity(), TraderCompanyDetailsContracts.IView {

    private lateinit var edtName: EditText
    private lateinit var edtCuisine: EditText
    private lateinit var edtDescrption: EditText
    private lateinit var imgCompany: ImageView
    private lateinit var btnNext: Button
    private lateinit var linearback: LinearLayout
    private lateinit var mPresenter: TraderCompanyDetailsContracts.IPresenter
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_details)
        initViews()
        initImagePicker()
        setListeners()
    }

    private fun setListeners() {
        btnNext.setOnClickListener {
            val name = edtName.text.toString().trim()
            val cuisine = edtCuisine.text.toString().trim()
            val description = edtDescrption.text.toString().trim()
            mPresenter.initValidation(name, cuisine, description, uri)
        }

        linearback.setOnClickListener {
            startActivity(Intent(this, ActivityCompanyType::class.java))
            finish()
        }

    }

    private fun initImagePicker() {
        imgCompany.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 99)
        }
    }

    private fun initViews() {
        edtName = findViewById(R.id.edt_company_name)
        edtCuisine = findViewById(R.id.edt_cuisine_type)
        edtDescrption = findViewById(R.id.edt_company_description)
        imgCompany = findViewById(R.id.img_company_photo)
        btnNext = findViewById(R.id.btn_trader_next)
        linearback = findViewById(R.id.linear_go_back)
        mPresenter = TraderCompanyDetailsPresenter(this@ActivityCompanyDetails, this)
    }

    override fun onValidationResult(valid: Boolean) {
        if (valid) {
            mPresenter.addCompanyDetails()
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAddCompanyDetailsResult(status: Boolean) {
        if (status) {
            startActivity(Intent(this, ActivityCompanyTimings::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 99 && resultCode == Activity.RESULT_OK && data != null) {
            uri = data.data!!
            imgCompany.setImageURI(uri)
        }
    }

    override fun onStart() {
        super.onStart()
        val pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("current_screen", "trader company details")
        editor.apply()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityTraderSignup::class.java))
        finish()
    }

}
