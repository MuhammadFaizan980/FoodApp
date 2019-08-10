package com.squadtechs.markhor.foodapp.customer

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityCustomerLogin : AppCompatActivity() {

    private lateinit var linearNoAccount: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        linearNoAccount.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerSignup::class.java))
        }
    }

    private fun initViews() {
        linearNoAccount = findViewById(R.id.linear_customer_no_account)
    }

}
