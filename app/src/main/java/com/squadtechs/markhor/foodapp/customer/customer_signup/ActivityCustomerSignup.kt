package com.squadtechs.markhor.foodapp.customer.customer_signup

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin

class ActivityCustomerSignup : AppCompatActivity(), CustomerRegistrationCnotracts.IView {

    private lateinit var email: EditText
    private lateinit var lastName: EditText
    private lateinit var firstName: EditText
    private lateinit var mobile: EditText
    private lateinit var birthday: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var agreement: RadioButton
    private lateinit var btnRegister: Button
    private lateinit var alreadyRegiseredView: LinearLayout
    private lateinit var mPresenter: CustomerRegistrationCnotracts.IPresenter

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

        alreadyRegiseredView.setOnClickListener {
            startActivity(Intent(this@ActivityCustomerSignup, ActivityCustomerLogin::class.java))
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
        alreadyRegiseredView = findViewById(R.id.linear_customer_no_account)
        mPresenter = CustomerRegistrationPresenter(this@ActivityCustomerSignup, this)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.initRegistration()
        } else {
            Toast.makeText(this@ActivityCustomerSignup, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRegistrationResult(status: Boolean) {
        if (status) {
            Toast.makeText(this@ActivityCustomerSignup, "Registration success", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@ActivityCustomerSignup, "Registration failed", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ActivityCustomerSignup, ActivityCustomerLogin::class.java))
        finish()
    }

}
