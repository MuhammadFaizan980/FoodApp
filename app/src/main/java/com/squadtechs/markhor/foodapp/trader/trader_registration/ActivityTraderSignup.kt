package com.squadtechs.markhor.foodapp.trader.trader_registration

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.trader.trader_login.ActivityTraderLogin

class ActivityTraderSignup : AppCompatActivity(), TraderRegistrationContracts.IView {

    private lateinit var email: EditText
    private lateinit var lastName: EditText
    private lateinit var firstName: EditText
    private lateinit var mobile: EditText
    private lateinit var companyMobile: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var agreement: RadioButton
    private lateinit var btnRegister: Button
    private lateinit var traderLogin: LinearLayout
    private lateinit var iAmCustomer: LinearLayout
    private lateinit var mPresenter: TraderRegistrationContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_signup)
        initVIews()
        setListeners()
    }

    private fun setListeners() {
        btnRegister.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userpassword = password.text.toString().trim()
            val userFirstName = firstName.text.toString().trim()
            val userLastName = lastName.text.toString().trim()
            val userConfirmPassword = confirmPassword.text.toString().trim()
            val mobile = mobile.text.toString().trim()
            val companyMobil = companyMobile.text.toString().trim()
            mPresenter.initValidation(
                userFirstName,
                userLastName,
                mobile,
                companyMobil,
                userEmail,
                userpassword,
                userConfirmPassword,
                agreement.isChecked
            )
        }

        traderLogin.setOnClickListener {
            startActivity(Intent(this@ActivityTraderSignup, ActivityTraderLogin::class.java))
            finish()
        }
        iAmCustomer.setOnClickListener {
            startActivity(Intent(this@ActivityTraderSignup, ActivityCustomerLogin::class.java))
            finish()
        }


    }

    private fun initVIews() {
        traderLogin = findViewById(R.id.linear_trader_login)
        iAmCustomer = findViewById(R.id.linear_i_am_customer)
        email = findViewById(R.id.edt_trader_email)
        firstName = findViewById(R.id.edt_trader_first_name)
        lastName = findViewById(R.id.edt_trader_last_name)
        mobile = findViewById(R.id.edt_trader_mobile_number)
        companyMobile = findViewById(R.id.edt_trader_company_landline)
        password = findViewById(R.id.edt_trader_password)
        confirmPassword = findViewById(R.id.edt_trader_confirm_password)
        agreement = findViewById(R.id.radio_trader_terms_and_condition)
        btnRegister = findViewById(R.id.btn_trader_sign_up)
        mPresenter = TraderRegistrationPresenter(this@ActivityTraderSignup, this)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.initRegistration()
        } else {
            Toast.makeText(this@ActivityTraderSignup, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRegistrationResult(status: Boolean) {
        if (status) {
            Toast.makeText(this@ActivityTraderSignup, "Registration success", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@ActivityTraderSignup, "Registration error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ActivityTraderSignup, ActivityTraderLogin::class.java))
        finish()
    }

}
