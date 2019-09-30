package com.squadtechs.markhor.foodapp.customer.customer_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.activity_select.ActivitySelect
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain
import com.squadtechs.markhor.foodapp.customer.customer_signup.ActivityCustomerSignup
import com.squadtechs.markhor.foodapp.trader.trader_login.ActivityTraderLogin

class ActivityCustomerLogin : AppCompatActivity(), CustomerLoginContracts.IView {

    private lateinit var linearNoAccount: LinearLayout
    private lateinit var linearTraderLogin: LinearLayout
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var mPresenter: CustomerLoginContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        linearNoAccount.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerSignup::class.java))
            finish()
        }
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            mPresenter.initValidation(email, password)
        }

        linearTraderLogin.setOnClickListener {
            startActivity(Intent(this@ActivityCustomerLogin, ActivityTraderLogin::class.java))
            finish()
        }

    }

    private fun initViews() {
        linearNoAccount = findViewById(R.id.linear_customer_no_account)
        edtEmail = findViewById(R.id.edt_customer_email)
        edtPassword = findViewById(R.id.edt_customer_password)
        btnLogin = findViewById(R.id.btn_customer_login)
        mPresenter = CustomerLoginPresenter(this@ActivityCustomerLogin, this)
        linearTraderLogin = findViewById(R.id.linear_i_am_trader)
    }

    override fun onValidationResult(status: Boolean, message: String) {
        if (status) {
            mPresenter.initLogin()
        } else {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onLoginResult(status: Boolean, message: String) {
        if (status) {
            startActivity(Intent(this, ActivityCustomerMain::class.java))
            finish()
        } else if (!message.equals("")) {
            Toast.makeText(this, "Login Failed, $message", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivitySelect::class.java))
        finish()
    }

}
