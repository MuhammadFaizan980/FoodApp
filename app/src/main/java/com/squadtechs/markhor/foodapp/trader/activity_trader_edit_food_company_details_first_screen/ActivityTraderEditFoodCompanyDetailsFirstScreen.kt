package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_food_company_details_first_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_profile.ActivityTraderEditProfile

class ActivityTraderEditFoodCompanyDetailsFirstScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_food_company_details_first_screen)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityTraderEditProfile::class.java))
        finish()
    }

}
