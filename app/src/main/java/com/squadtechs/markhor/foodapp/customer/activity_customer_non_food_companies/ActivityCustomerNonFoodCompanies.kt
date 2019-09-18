package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain

class ActivityCustomerNonFoodCompanies : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_non_food_companies)

    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCustomerMain::class.java))
        finish()
    }

}
