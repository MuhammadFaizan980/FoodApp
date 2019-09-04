package com.squadtechs.markhor.foodapp.customer.activity_phone_verification

import com.google.firebase.auth.PhoneAuthCredential

interface PhoneVerificationContracts {
    interface IPresenter {
        fun sendVerificationCode(phone_number: String)
        fun verifyCode(credentials: PhoneAuthCredential)
    }

    interface IView {
        fun onSendVerificationCodeError()
        fun onCodeSentResult(id: String)
        fun onCodeVerificationResult(status: Boolean)
    }
}