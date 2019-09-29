package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_food_company_details_first_screen.ActivityTraderEditFoodCompanyDetailsFirstScreen
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_non_food_company_details_first_screen.ActivityTraderEditNonFoodCompanyDetailsFirstScreen
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.ActivityTraderMain

class ActivityTraderEditProfile : AppCompatActivity(), TraderEditProfileMainContracts.IView {

    private lateinit var btnBack: ImageView
    private lateinit var edtFirstName: EditText
    private lateinit var edtLastName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var btnSaveData: Button
    private lateinit var mPresenter: TraderEditProfileMainContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_profile)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        btnBack.setOnClickListener {
            finish()
        }
        btnSaveData.setOnClickListener {
            mPresenter.initValidation(
                edtFirstName.text.toString().trim(),
                edtLastName.text.toString().trim(),
                edtPhone.text.toString().trim()
            )
        }
    }

    private fun initViews() {
        btnBack = findViewById(R.id.img_go_back)
        edtFirstName = findViewById(R.id.edt_first_name)
        edtLastName = findViewById(R.id.edt_last_name)
        edtPhone = findViewById(R.id.edt_mobile_number)
        btnSaveData = findViewById(R.id.btn_save_trader_profile_changes)
        mPresenter = TraderEditProfilePresenter(this@ActivityTraderEditProfile, this)
    }


    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.editProfile()
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onEditProfileResult(status: Boolean) {
        if (status) {

            if (getSharedPreferences(
                    "user_credentials",
                    Context.MODE_PRIVATE
                ).getString("company_type", "n/a").equals("Food & beverages")
            ) {
                startActivity(
                    Intent(
                        this,
                        ActivityTraderEditFoodCompanyDetailsFirstScreen::class.java
                    )
                )
            } else {
                startActivity(
                    Intent(
                        this,
                        ActivityTraderEditNonFoodCompanyDetailsFirstScreen::class.java
                    )
                )
            }
            finish()
        } else {
            Toast.makeText(this, "There was an error updating your profile", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onBackPressed() {
        finish()
    }

}
