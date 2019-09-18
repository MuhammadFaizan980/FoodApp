package com.squadtechs.markhor.foodapp.customer.activity_customer_address

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_add_address.ActivityCustomerAddAddress
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain

class ActivityCustomerAddress : AppCompatActivity() {

    private lateinit var txtAddNewAddress: TextView
    private lateinit var imgGoBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_address)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        txtAddNewAddress.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerAddAddress::class.java))
            finish()
        }

        imgGoBack.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerMain::class.java))
            finish()
        }
    }

    private fun initViews() {
        txtAddNewAddress = findViewById(R.id.txt_customer_address)
        imgGoBack = findViewById(R.id.img_go_back)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCustomerMain::class.java))
        finish()
    }

}
