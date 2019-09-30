package com.squadtechs.markhor.foodapp.trader.trader_registration

import android.util.Patterns

class TraderRegistrationModel(
    private val mPresenter: TraderRegistrationContracts.IPresenter,
    private val firstName: String,
    private val lastName: String,
    private val mobileNumber: String,
    private val companyMobile: String,
    private val email: String,
    private val password: String,
    private val confirmPassword: String,
    private val agreement: Boolean
) : TraderRegistrationContracts.IModel {
    override fun validateFields() {
        if (firstName.isEmpty()) {
            mPresenter.validationCallback(false, "First name cannot be blank")
        } else if (lastName.isEmpty()) {
            mPresenter.validationCallback(false, "Last name cannot be empty")
        } else if (mobileNumber.isEmpty()) {
            mPresenter.validationCallback(false, "Mobile number cannot be empty")
        } else if (email.isEmpty()) {
            mPresenter.validationCallback(false, "Email cannot be empty")
        } else if (password.isEmpty()) {
            mPresenter.validationCallback(false, "Password cannot be blank")
        } else if (password.length < 6) {
            mPresenter.validationCallback(false, "Password must contain at least 6 characters")
        } else if (confirmPassword.isEmpty()) {
            mPresenter.validationCallback(false, "Confirm password cannot be empty")
        } else if (!agreement) {
            mPresenter.validationCallback(false, "You must agree to terms and conditions")
        } else if (password != confirmPassword) {
            mPresenter.validationCallback(false, "Passwords do not match")
        } else if (!validateEmail()) {
            mPresenter.validationCallback(false, "Invalid email address")
        } else {
            mPresenter.validationCallback(true, "none")
        }
    }

    private fun validateEmail(): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}