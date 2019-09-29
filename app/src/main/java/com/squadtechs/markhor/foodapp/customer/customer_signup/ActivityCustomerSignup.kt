package com.squadtechs.markhor.foodapp.customer.customer_signup

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_phone_verification.ActivityPhoneVerification
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.trader.trader_login.ActivityTraderLogin

class ActivityCustomerSignup : AppCompatActivity(), CustomerRegistrationContracts.IView {

    private lateinit var email: EditText
    private lateinit var lastName: EditText
    private lateinit var firstName: EditText
    private lateinit var mobile: EditText
    private lateinit var birthday: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var agreement: RadioButton
    private lateinit var btnRegister: Button
    private lateinit var alreadyRegisteredView: LinearLayout
    private lateinit var traderRegister: LinearLayout
    private lateinit var mPresenter: CustomerRegistrationContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_signup)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        btnRegister.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()
            val userConfirmPassword = confirmPassword.text.toString()
            val userFirstName = firstName.text.toString()
            val userLastName = lastName.text.toString()
            val userPhone = mobile.text.toString()
            val userBirthday = birthday.text.toString()
            mPresenter.initValidation(
                userFirstName,
                userLastName,
                userPhone,
                userBirthday,
                userEmail,
                userPassword,
                userConfirmPassword,
                agreement.isChecked
            )
        }

        alreadyRegisteredView.setOnClickListener {
            startActivity(Intent(this@ActivityCustomerSignup, ActivityCustomerLogin::class.java))
            finish()
        }

        traderRegister.setOnClickListener {
            startActivity(Intent(this@ActivityCustomerSignup, ActivityTraderLogin::class.java))
            finish()
        }

    }

    private fun initViews() {
        email = findViewById(R.id.edt_customer_email)
        firstName = findViewById(R.id.edt_customer_first_name)
        lastName = findViewById(R.id.edt_customer_last_name)
        mobile = findViewById(R.id.edt_customer_mobile_number)
        birthday = findViewById(R.id.edt_customer_birth_day)
        password = findViewById(R.id.edt_customer_password)
        confirmPassword = findViewById(R.id.edt_customer_confirm_password)
        agreement = findViewById(R.id.radio_customer_terms_and_condition)
        btnRegister = findViewById(R.id.btn_customer_sign_up)
        alreadyRegisteredView = findViewById(R.id.linear_customer_no_account)
        mPresenter = CustomerRegistrationPresenter(this@ActivityCustomerSignup, this)
        traderRegister = findViewById(R.id.linear_i_am_trader)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.initRegistration()
        } else {
            Toast.makeText(this@ActivityCustomerSignup, "Invalid credentials", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onRegistrationResult(status: Boolean, message: String) {
        if (status) {
            val intent = Intent(this, ActivityPhoneVerification::class.java)
            intent.putExtra("phone_number", mobile.text.toString().trim())
            startActivity(intent)
            finish()
        } else if (!message.equals("")) {
            Toast.makeText(
                this@ActivityCustomerSignup,
                "Registration failed $message",
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            Toast.makeText(this@ActivityCustomerSignup, "Registration failed", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ActivityCustomerSignup, ActivityCustomerLogin::class.java))
        finish()
    }

}
