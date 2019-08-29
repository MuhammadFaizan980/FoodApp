package com.squadtechs.markhor.foodapp.activity_select

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.trader.ActivityCompanyType
import com.squadtechs.markhor.foodapp.trader.trader_login.ActivityTraderLogin

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
        startActivity(Intent(this, ActivityCompanyType::class.java))
        finish()
    }

}
