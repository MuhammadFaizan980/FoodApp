package com.squadtechs.markhor.foodapp.customer.activity_phone_verification

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.squadtechs.markhor.foodapp.R
import java.util.concurrent.TimeUnit

class ActivityPhoneVerification : AppCompatActivity() {

    private lateinit var edtFirst: EditText
    private lateinit var edtSecond: EditText
    private lateinit var edtThird: EditText
    private lateinit var edtFourth: EditText
    private lateinit var edtFifth: EditText
    private lateinit var edtSixth: EditText
    private lateinit var btnVerify: Button
    private lateinit var linearBack: LinearLayout
    private lateinit var phoneAuthProvider: PhoneAuthProvider
    private lateinit var phone_number: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)
        initViews()
        initPhoneAuthentication()
    }

    private fun initPhoneAuthentication() {
        phoneAuthProvider.verifyPhoneNumber(
            phone_number,
            60L,
            TimeUnit.SECONDS, this,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    TODO("not implemented")
                }

            })
    }

    private fun initViews() {
        edtFirst = findViewById(R.id.edt_verification_first)
        edtSecond = findViewById(R.id.edt_verification_second)
        edtThird = findViewById(R.id.edt_verification_third)
        edtFourth = findViewById(R.id.edt_verification_fourth)
        edtFifth = findViewById(R.id.edt_verification_fifth)
        edtSixth = findViewById(R.id.edt_verification_sixth)
        btnVerify = findViewById(R.id.btn_thank_you_done)
        linearBack = findViewById(R.id.linear_trader_already_registered)
        phoneAuthProvider = PhoneAuthProvider.getInstance()
        phone_number = intent!!.extras!!.getString("phone_number", "n/a")
    }

}
