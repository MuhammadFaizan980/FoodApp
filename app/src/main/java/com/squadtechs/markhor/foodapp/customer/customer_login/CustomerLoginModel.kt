package com.squadtechs.markhor.foodapp.customer.customer_login

class CustomerLoginModel(val email: String, val password: String) : CustomerLoginContracts.IModel {
    override fun validateFields(): Boolean = email.isNotEmpty() && password.isNotEmpty()
}