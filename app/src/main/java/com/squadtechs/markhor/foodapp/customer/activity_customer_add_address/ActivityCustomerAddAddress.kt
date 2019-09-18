package com.squadtechs.markhor.foodapp.customer.activity_customer_add_address

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityCustomerAddAddress : AppCompatActivity() {
    private lateinit var imgGoBack: ImageView
    private lateinit var btnSaveAddress: Button
    private lateinit var edtAddess: EditText
    private lateinit var addressCheckBox: CheckBox
    private var isDefault: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_add_address)
        initVIews()
    }

    private fun initVIews() {
        imgGoBack = findViewById(R.id.img_go_back)
        edtAddess = findViewById(R.id.edt_customer_address)
        btnSaveAddress = findViewById(R.id.btn_save_customer_address)
        addressCheckBox = findViewById(R.id.check_box_customer_address)
    }

}
