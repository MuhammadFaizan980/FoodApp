package com.squadtechs.markhor.foodapp.trader.trader_registration

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.activity_select.ActivitySelect
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.trader.activity_electronic_license.ActivityElectronicLicense
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
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
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
        pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        editor = pref.edit()
        mPresenter = TraderRegistrationPresenter(this@ActivityTraderSignup, this)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.initRegistration()
        } else {
            Toast.makeText(this@ActivityTraderSignup, "Invalid credentials", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onRegistrationResult(status: Boolean, message: String) {
        if (status) {
            showDialog()
        } else {
            Toast.makeText(
                this@ActivityTraderSignup,
                "Registration error $message",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ActivityTraderSignup, ActivitySelect::class.java))
        finish()
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(this@ActivityTraderSignup)
        dialog.setTitle("Message!")
        dialog.setMessage("Trader account has been created\nYou will need to upload electronic license in order to confirm your trader account in the next screen")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Next") { dialogInterface, i ->
            startActivity(Intent(this@ActivityTraderSignup, ActivityElectronicLicense::class.java))
            finish()
        }
        dialog.show()
    }

}
