package com.squadtechs.markhor.foodapp.customer.activity_customer_change_password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin

class ActivityCustomerChangePassword : AppCompatActivity(),
    CustomerChangePasswordMainContracts.IView {
    private lateinit var btnBack: ImageView
    private lateinit var edtCurrentPassword: EditText
    private lateinit var edtNewPassword: EditText
    private lateinit var edtConfirmNewPassword: EditText
    private lateinit var btnSaveData: Button
    private lateinit var mPresenter: CustomerChangePasswordMainContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_change_password)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        btnBack.setOnClickListener {
            finish()
        }
        btnSaveData.setOnClickListener {
            val currentSavedPassword = getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("user_password", "n/a") as String
            mPresenter.initValidation(
                edtCurrentPassword.text.toString().trim(),
                currentSavedPassword,
                edtNewPassword.text.toString().trim(),
                edtConfirmNewPassword.text.toString().trim()
            )
        }
    }

   private  fun initViews() {
        btnBack = findViewById(R.id.img_go_back)
        edtCurrentPassword = findViewById(R.id.edt_current_password)
        edtNewPassword = findViewById(R.id.edt_new_password)
        edtConfirmNewPassword = findViewById(R.id.edt_new_password)
        btnSaveData = findViewById(R.id.btn_save_customer_password_changes)
        mPresenter = CustomerChangePasswordPresenter(this@ActivityCustomerChangePassword, this)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.changePassword()
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onChangePasswordResult(status: Boolean) {
        if (status) {
            startActivity(Intent(this, ActivityCustomerLogin::class.java))
            finish()
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        finish()
    }

}
