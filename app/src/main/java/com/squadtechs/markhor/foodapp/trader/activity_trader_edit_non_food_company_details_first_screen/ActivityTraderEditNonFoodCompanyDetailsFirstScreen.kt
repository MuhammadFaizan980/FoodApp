package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_non_food_company_details_first_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_logo.ActivityTraderEditCompanyLogo
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_profile.ActivityTraderEditProfile

class ActivityTraderEditNonFoodCompanyDetailsFirstScreen : AppCompatActivity(),
    EditNonFoodContracts.IView {

    private lateinit var spinnerShopType: Spinner
    private var cuisine: String = "Clothes"
    private lateinit var companyName: String
    private lateinit var companyDescription: String
    private lateinit var companyPhone: String
    private lateinit var btnNext: Button
    private lateinit var imgBack: ImageView
    private lateinit var edtTitle: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtPhone: EditText
    private lateinit var mPresenter: EditNonFoodContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_non_food_company_details_first_screen)
        initViews()
        populateSpinner()
        setListeners()
    }

    private fun setListeners() {
        imgBack.setOnClickListener {
            startActivity(Intent(this, ActivityTraderEditProfile::class.java))
            finish()
        }

        btnNext.setOnClickListener {
            companyName = edtTitle.text.toString().trim()
            companyPhone = edtPhone.text.toString().trim()
            companyDescription = edtDescription.text.toString().trim()
            mPresenter.initValidation(companyName, companyPhone, cuisine, companyDescription)
        }

    }

    private fun populateSpinner() {
        val arr = arrayOfNulls<String>(8)
        arr[0] = "Clothes"
        arr[1] = "Accessories"
        arr[2] = "Skincare"
        arr[3] = "Homeware"
        arr[4] = "Toys"
        arr[5] = "Sheos"
        arr[6] = "Bags"
        arr[7] = "Other"
        val arrAdapter = ArrayAdapter<String>(
            this,
            R.layout.shop_type_spinner_row_design,
            R.id.txt_shop_type,
            arr
        )
        spinnerShopType.adapter = arrAdapter

        spinnerShopType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                cuisine = arr[p2] as String
            }

        }

    }

    private fun initViews() {
        spinnerShopType = findViewById(R.id.spinner_shop_type)
        btnNext = findViewById(R.id.btn_trader_edit_company_next)
        imgBack = findViewById(R.id.img_go_back)
        edtTitle = findViewById(R.id.edt_company_name)
        edtDescription = findViewById(R.id.edt_company_description)
        edtPhone = findViewById(R.id.edt_company_phone)
        mPresenter = EditNonFoodPresenter(this@ActivityTraderEditNonFoodCompanyDetailsFirstScreen)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            val intent = Intent(this, ActivityTraderEditCompanyLogo::class.java)
            intent.putExtra("company_name", companyName)
            intent.putExtra("company_cuisine", cuisine)
            intent.putExtra("company_phone", companyPhone)
            intent.putExtra("company_description", companyDescription)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityTraderEditProfile::class.java))
        finish()
    }

}
