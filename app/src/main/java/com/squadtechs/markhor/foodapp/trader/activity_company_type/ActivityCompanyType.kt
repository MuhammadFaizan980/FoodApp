package com.squadtechs.markhor.foodapp.trader.activity_company_type

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.activity_select.ActivitySelect
import com.squadtechs.markhor.foodapp.trader.activity_company_details.ActivityCompanyDetails

class ActivityCompanyType : AppCompatActivity() {

    private lateinit var btnNext: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var edtType: EditText
    private lateinit var radioFood: RadioButton
    private lateinit var radioClothes: RadioButton
    private lateinit var radioAccessories: RadioButton
    private lateinit var radioSkincare: RadioButton
    private lateinit var radioHomeware: RadioButton
    private lateinit var radioToys: RadioButton
    private lateinit var radioShoes: RadioButton
    private lateinit var radioBags: RadioButton
    private lateinit var radioOthers: RadioButton
    private lateinit var linearback: LinearLayout
    private lateinit var selectedValue: String
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_type)
        initViews()
        setLisetener()
    }

    private fun setLisetener() {
        btnNext.setOnClickListener {

            if (radioOthers.isChecked) {
                selectedValue = edtType.text.toString().trim()
            }

            if (selectedValue.equals("")) {
                Toast.makeText(
                    this,
                    "Select a proper type first, if others then please specify",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val pref = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("company_type", selectedValue)
                editor.apply()
                startActivity(Intent(this, ActivityCompanyDetails::class.java))
                finish()
            }
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (radioFood.isChecked) {
                selectedValue = "Food & beverages"
                edtType.isEnabled = false
            } else if (radioClothes.isChecked) {
                selectedValue = "Clothes"
                edtType.isEnabled = false
            } else if (radioAccessories.isChecked) {
                selectedValue = "Accessories"
                edtType.isEnabled = false
            } else if (radioSkincare.isChecked) {
                selectedValue = "Skincare"
                edtType.isEnabled = false
            } else if (radioHomeware.isChecked) {
                selectedValue = "Homeware"
                edtType.isEnabled = false
            } else if (radioToys.isChecked) {
                selectedValue = "Toys"
                edtType.isEnabled = false
            } else if (radioShoes.isChecked) {
                selectedValue = "Shoes"
                edtType.isEnabled = false
            } else if (radioBags.isChecked) {
                selectedValue = "Bags"
                edtType.isEnabled = false
            } else if (radioOthers.isChecked) {
                edtType.isEnabled = true
                selectedValue = ""
            }
        }

        linearback.setOnClickListener {
            startActivity(Intent(this, ActivitySelect::class.java))
            finish()
        }

    }

    private fun initViews() {
        btnNext = findViewById(R.id.btn_trader_next)
        radioGroup = findViewById(R.id.m_group)
        edtType = findViewById(R.id.edt_other)
        radioFood = findViewById(R.id.radio_food)
        radioClothes = findViewById(R.id.radio_clothes)
        radioAccessories = findViewById(R.id.radio_accessories)
        radioSkincare = findViewById(R.id.radio_skin_care)
        radioHomeware = findViewById(R.id.radio_home_ware)
        radioToys = findViewById(R.id.radio_toys)
        radioShoes = findViewById(R.id.radio_shoes)
        radioBags = findViewById(R.id.radio_bags)
        radioOthers = findViewById(R.id.radio_other)
        linearback = findViewById(R.id.linear_go_back)
        pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        editor = pref.edit()
        selectedValue = ""
        edtType.isEnabled = false
    }

    override fun onStart() {
        super.onStart()
        editor.putString("current_screen", "company type")
        editor.apply()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivitySelect::class.java))
        finish()
    }

}
