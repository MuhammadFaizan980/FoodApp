package com.squadtechs.markhor.foodapp.trader.trader_login

class TraderLoginModel(private val email: String, private val password: String) : TraderLoginContracts.IModel {
    override fun validateFields(): Boolean =
        email.isNotEmpty() && password.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}