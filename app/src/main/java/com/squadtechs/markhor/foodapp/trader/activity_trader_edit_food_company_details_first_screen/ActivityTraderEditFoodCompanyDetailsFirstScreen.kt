package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_food_company_details_first_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_details_second_screen.ActivityTraderEditCompanyDetailsSecondScreen
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_profile.ActivityTraderEditProfile

class ActivityTraderEditFoodCompanyDetailsFirstScreen : AppCompatActivity(),
    TraderEditFoodCompanyFirstScreenMainContracts.IView {

    private lateinit var btnNext: Button
    private lateinit var imgBack: ImageView
    private lateinit var edtTitle: EditText
    private lateinit var edtCuisine: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtPhone: EditText
    private lateinit var mPresenter: TraderEditFoodCompanyFirstScreenMainContracts.IPresenter
    private lateinit var companyName: String
    private lateinit var companyCuisine: String
    private lateinit var companyPhone: String
    private lateinit var companyDescrption: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_food_company_details_first_screen)
        initViews()
        setListeners()
    }

    private fun setListeners() {

        imgBack.setOnClickListener {
            startActivity(Intent(this, ActivityTraderEditProfile::class.java))
            finish()
        }

        btnNext.setOnClickListener {
            companyName = edtTitle.text.toString().trim()
            companyCuisine = edtCuisine.text.toString().trim()
            companyPhone = edtPhone.text.toString().trim()
            companyDescrption = edtDescription.text.toString().trim()
            mPresenter.initValidation(companyName, companyPhone, companyCuisine, companyDescrption)
        }
    }

    private fun initViews() {
        btnNext = findViewById(R.id.btn_trader_edit_company_next)
        imgBack = findViewById(R.id.img_go_back)
        edtTitle = findViewById(R.id.edt_company_name)
        edtCuisine = findViewById(R.id.edt_cuisine_type)
        edtDescription = findViewById(R.id.edt_company_description)
        edtPhone = findViewById(R.id.edt_company_phone)
        mPresenter = EditFoodPresenter(this@ActivityTraderEditFoodCompanyDetailsFirstScreen)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            val intent = Intent(this, ActivityTraderEditCompanyDetailsSecondScreen::class.java)
            intent.putExtra("company_name", companyName)
            intent.putExtra("company_cuisine", companyCuisine)
            intent.putExtra("company_phone", companyPhone)
            intent.putExtra("company_description", companyDescrption)
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
