package com.squadtechs.markhor.foodapp.trader.trader_login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import com.squadtechs.markhor.foodapp.trader.activity_company_details.ActivityCompanyDetails
import com.squadtechs.markhor.foodapp.trader.activity_company_timings.ActivityCompanyTimings
import com.squadtechs.markhor.foodapp.trader.activity_company_type.ActivityCompanyType
import com.squadtechs.markhor.foodapp.trader.activity_delivery_details.ActivityDeliveryDetails
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.ActivityTraderMain
import com.squadtechs.markhor.foodapp.trader.trader_registration.ActivityTraderSignup

class ActivityTraderLogin : AppCompatActivity(), TraderLoginContracts.IView {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var linearCustomer: LinearLayout
    private lateinit var linearTraderNoAccount: LinearLayout
    private lateinit var mPresenter: TraderLoginContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_login)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            mPresenter.initValidation(email, password)
        }

        linearCustomer.setOnClickListener {
            startActivity(Intent(this@ActivityTraderLogin, ActivityCustomerLogin::class.java))
            finish()
        }

        linearTraderNoAccount.setOnClickListener {
            startActivity(Intent(this@ActivityTraderLogin, ActivityTraderSignup::class.java))
            finish()
        }

    }

    private fun initViews() {
        edtEmail = findViewById(R.id.edt_trader_email)
        edtPassword = findViewById(R.id.edt_trader_password)
        btnLogin = findViewById(R.id.btn_trader_login)
        mPresenter = TraderLoginPresenter(this@ActivityTraderLogin, this)
        linearCustomer = findViewById(R.id.linear_i_am_customer)
        linearTraderNoAccount = findViewById(R.id.linear_trader_no_account)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.initLogin()
        } else {
            Toast.makeText(this@ActivityTraderLogin, "Invalid credentials", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onLoginResult(
        status: Boolean,
        approval: String,
        profileStatus: String,
        message: String
    ) {
        if (status) {
            Log.i("m_resp", approval)
            if (approval.equals("pending")) {
                val dialog = MainUtils.getMessageDialog(
                    this,
                    "Message!",
                    "Your account is not approved yet\nYou will be able to login once your account is approved by admin\nIncomplete registration will result in account suspension",
                    false
                )
                dialog.setPositiveButton("Ok") { dialogInterface, i ->
                    dialogInterface.cancel()
                }
                dialog.show()
            } else if (profileStatus.equals("yes")) {
                startActivity(Intent(this, ActivityTraderMain::class.java))
                finish()
            } else {
                val isProfileInProgress =
                    getSharedPreferences("reg_progress", Context.MODE_PRIVATE).getString(
                        "current_screen",
                        "null"
                    ) as String
                if (!isProfileInProgress.equals("prof_complete") && !profileStatus.equals("yes")) {

                    when (isProfileInProgress) {
                        "trader company details" -> {
                            startActivity(Intent(this, ActivityCompanyDetails::class.java))
                            finish()
                        }
                        "trader company timings" -> {
                            startActivity(Intent(this, ActivityCompanyTimings::class.java))
                            finish()
                        }
                        "trader delivery details" -> {
                            startActivity(Intent(this, ActivityDeliveryDetails::class.java))
                            finish()
                        }
                        else -> {
                            startActivity(Intent(this, ActivityCompanyType::class.java))
                            finish()
                        }
                    }
                } else {
                    startActivity(Intent(this, ActivityTraderMain::class.java))
                    finish()
                }
            }
        } else {
            Toast.makeText(this@ActivityTraderLogin, "Login error, $message", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ActivityTraderLogin, ActivityTraderSignup::class.java))
        finish()
    }

}
