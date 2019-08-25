package com.squadtechs.markhor.foodapp.customer.customer_login

class CustomerLoginModel(private val email: String, private val password: String) : CustomerLoginContracts.IModel {
    override fun validateFields(): Boolean =
        email.isNotEmpty() && password.isNotEmpty() //&& android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}