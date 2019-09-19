package com.squadtechs.markhor.foodapp.customer.activity_customer_add_address

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_address.ActivityCustomerAddress
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain

class ActivityCustomerAddAddress : AppCompatActivity(), CustomerAddAddressMainContracts.IView {

    private lateinit var imgGoBack: ImageView
    private lateinit var btnSaveAddress: Button
    private lateinit var edtAddess: EditText
    private lateinit var addressCheckBox: CheckBox
    private lateinit var mPresenter: CustomerAddAddressMainContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_add_address)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        imgGoBack.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerAddress::class.java))
            finish()
        }
        btnSaveAddress.setOnClickListener {
            mPresenter.initValidation(
                edtAddess.text.toString(), if (addressCheckBox.isChecked) {
                    "yes"
                } else {
                    "no"
                }
            )
        }
    }

    private fun initViews() {
        imgGoBack = findViewById(R.id.img_go_back)
        edtAddess = findViewById(R.id.edt_customer_address)
        btnSaveAddress = findViewById(R.id.btn_save_customer_address)
        addressCheckBox = findViewById(R.id.check_box_customer_address)
        mPresenter = CustomerAddAddressPresenter(this@ActivityCustomerAddAddress, this)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.saveCustomerAddress()
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveAddressResult(status: Boolean) {
        if (status) {
            Toast.makeText(this, "Address added successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ActivityCustomerMain::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCustomerAddress::class.java))
        finish()
    }

}
