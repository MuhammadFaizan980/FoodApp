package com.squadtechs.markhor.foodapp.customer.customer_login

import android.util.Patterns

class CustomerLoginModel(
    private val mPresenter: CustomerLoginContracts.IPresenter,
    private val email: String,
    private val password: String
) : CustomerLoginContracts.IModel {
    override fun validateFields() {
        if (email.isEmpty() && password.isEmpty()) {
            mPresenter.validationCallback(false, "Email and password cannot be blank")
        } else if (email.isEmpty()) {
            mPresenter.validationCallback(false, "Email cannot blank")
        } else if (password.isEmpty()) {
            mPresenter.validationCallback(false, "Password can't be empty")
        } else if (!validEmail(email)) {
            mPresenter.validationCallback(false, "Invalid email address")
        } else if (password.length < 6) {
            mPresenter.validationCallback(
                false,
                "Password length must be at least 6 characters long"
            )
        } else {
            mPresenter.validationCallback(true, "none")
        }
    }


    private fun validEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}