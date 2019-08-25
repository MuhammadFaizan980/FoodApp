package com.squadtechs.markhor.foodapp.trader.trader_login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityTraderLogin : AppCompatActivity(), TraderLoginContracts.IView {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
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
    }

    private fun initViews() {
        edtEmail = findViewById(R.id.edt_trader_email)
        edtPassword = findViewById(R.id.edt_trader_password)
        btnLogin = findViewById(R.id.btn_trader_login)
        mPresenter = TraderLoginPresenter(this@ActivityTraderLogin, this)
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            mPresenter.initLogin()
        } else {
            Toast.makeText(this@ActivityTraderLogin, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onLoginResult(status: Boolean) {
        if (status) {
            Toast.makeText(this@ActivityTraderLogin, "Login success", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@ActivityTraderLogin, "Login error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {}

}
