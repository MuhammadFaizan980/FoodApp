package com.squadtechs.markhor.foodapp.customer.activity_phone_verification

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.PhoneAuthProvider
import com.squadtechs.markhor.foodapp.R

class ActivityPhoneVerification : AppCompatActivity(), PhoneVerificationContracts.IView {

    private lateinit var edtFirst: EditText
    private lateinit var edtSecond: EditText
    private lateinit var edtThird: EditText
    private lateinit var edtFourth: EditText
    private lateinit var edtFifth: EditText
    private lateinit var edtSixth: EditText
    private lateinit var btnVerify: Button
    private lateinit var linearResendCode: LinearLayout
    private lateinit var phoneAuthProvider: PhoneAuthProvider
    private lateinit var phone_number: String
    private lateinit var mPresenter: PhoneVerificationContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)
        initViews()
        mPresenter.sendVerificationCode(phone_number)
        setListener()
    }

    private fun setListener() {
        linearResendCode.setOnClickListener {
            mPresenter.sendVerificationCode(phone_number)
        }
    }

    private fun initViews() {
        edtFirst = findViewById(R.id.edt_verification_first)
        edtSecond = findViewById(R.id.edt_verification_second)
        edtThird = findViewById(R.id.edt_verification_third)
        edtFourth = findViewById(R.id.edt_verification_fourth)
        edtFifth = findViewById(R.id.edt_verification_fifth)
        edtSixth = findViewById(R.id.edt_verification_sixth)
        btnVerify = findViewById(R.id.btn_thank_you_done)
        linearResendCode = findViewById(R.id.linear_resend_verification_code)
        phoneAuthProvider = PhoneAuthProvider.getInstance()
        phone_number = intent!!.extras!!.getString("phone_number", "n/a")
        mPresenter = PhoneVerificationPresenter(this@ActivityPhoneVerification, this)
    }

    override fun onSendVerificationCodeError() {
        Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
    }

    override fun onCodeSentResult(id: String) {
        btnVerify.setOnClickListener {
            val code =
                edtFirst.text.toString().trim() + edtSecond.text.toString().trim() + edtThird.text.toString().trim() + edtFourth.text.toString().trim() + edtFifth.text.toString().trim() + edtSixth.text.toString().trim()
            val credentials = PhoneAuthProvider.getCredential(id, code)
            mPresenter.verifyCode(credentials)
        }
    }

    override fun onCodeVerificationResult(status: Boolean) {
        if (status) {
            Toast.makeText(this, "Verification success", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Verification error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {}
}
