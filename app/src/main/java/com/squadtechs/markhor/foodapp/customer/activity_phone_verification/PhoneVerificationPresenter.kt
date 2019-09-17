package com.squadtechs.markhor.foodapp.customer.activity_phone_verification

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import java.util.concurrent.TimeUnit

class PhoneVerificationPresenter(
    private val mView: PhoneVerificationContracts.IView,
    private val context: Context
) : PhoneVerificationContracts.IPresenter {

    private lateinit var phoneAuthProvider: PhoneAuthProvider
    private lateinit var mAuth: FirebaseAuth

    override fun sendVerificationCode(phone_number: String) {
        val pd = MainUtils.getLoadingDialog(context, "Wait", "Sending verification message", false)
        pd.show()
        phoneAuthProvider = PhoneAuthProvider.getInstance()
        phoneAuthProvider.verifyPhoneNumber(
            phone_number,
            60L,
            TimeUnit.SECONDS, (context as Activity),
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    //TODO: do something
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    pd.cancel()
                    mView.onSendVerificationCodeError(p0.message!!)
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    pd.cancel()
                    Toast.makeText(
                        context,
                        "A message has been sent to your number",
                        Toast.LENGTH_LONG
                    ).show()
                    mView.onCodeSentResult(p0)
                }

            })
    }

    override fun verifyCode(credentials: PhoneAuthCredential) {
        mAuth = FirebaseAuth.getInstance()
        val pd = ProgressDialog(context)
        pd.setCancelable(false)
        pd.setTitle("Wait")
        pd.setMessage("Verifying phone number")
        pd.show()
        mAuth.signInWithCredential(credentials).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                pd.cancel()
                mView.onCodeVerificationResult(true)
            } else {
                pd.cancel()
                mView.onCodeVerificationResult(false)
            }
        }
    }

}