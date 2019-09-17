package com.squadtechs.markhor.foodapp.activity_select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.trader.trader_registration.ActivityTraderSignup

class ActivitySelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
    }

    fun openCustomer(view: View) {
        startActivity(Intent(this, ActivityCustomerLogin::class.java))
        finish()
    }

    fun openTrader(view: View) {

        startActivity(Intent(this, ActivityTraderSignup::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        val pref = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
        val customerStatus = pref.getBoolean("customer_logged_in", false)
        val traderStatus = pref.getBoolean("trader_logged_in", false)
        if (customerStatus) {
            startActivity(Intent(this, ActivityCustomerMain::class.java))
            finish()
        } else if (traderStatus) {
            //TODO: take user to trade main screen
        }
    }

}
