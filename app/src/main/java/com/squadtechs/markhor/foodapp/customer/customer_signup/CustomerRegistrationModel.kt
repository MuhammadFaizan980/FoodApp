package com.squadtechs.markhor.foodapp.customer.customer_signup

import android.util.Patterns


class CustomerRegistrationModel(
    private val mPresenter: CustomerRegistrationContracts.IPresenter,
    private val firstName: String,
    private val lastName: String,
    private val mobileNumber: String,
    private val birthday: String,
    private val email: String,
    private val password: String,
    private val confirmPassword: String,
    private val userAgreement: Boolean
) : CustomerRegistrationContracts.IModel {
    override fun validate() {
        if (firstName.isEmpty()) {
            mPresenter.validationCallback(false, "First name cannot be blank")
        } else if (lastName.isEmpty()) {
            mPresenter.validationCallback(false, "Last name cannot be empty")
        } else if (mobileNumber.isEmpty()) {
            mPresenter.validationCallback(false, "Mobile number cannot be empty")
        } else if (!mobileNumber.startsWith("+")) {
            mPresenter.validationCallback(false, "Country code must be added with phone number")
        } else if (mobileNumber.contains("-")) {
            mPresenter.validationCallback(false, "Mobile number should not contain dashes")
        } else if (birthday.isEmpty()) {
            mPresenter.validationCallback(false, "Birthday cannot be blank")
        } else if (email.isEmpty()) {
            mPresenter.validationCallback(false, "Email cannot be empty")
        } else if (password.isEmpty()) {
            mPresenter.validationCallback(false, "Password cannot be blank")
        } else if (password.length < 6) {
            mPresenter.validationCallback(false, "Password must contain at least 6 characters")
        } else if (confirmPassword.isEmpty()) {
            mPresenter.validationCallback(false, "Confirm password cannot be empty")
        } else if (!userAgreement) {
            mPresenter.validationCallback(false, "You must agree to terms and conditions")
        } else if (password != confirmPassword) {
            mPresenter.validationCallback(false, "Passwords do not match")
        } else if (!validEmail(email)) {
            mPresenter.validationCallback(false, "Invalid email address")
        } else {
            mPresenter.validationCallback(true, "none")
        }
    }

    private fun validEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}