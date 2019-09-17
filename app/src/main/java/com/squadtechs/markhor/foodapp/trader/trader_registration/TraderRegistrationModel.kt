package com.squadtechs.markhor.foodapp.trader.trader_registration

class TraderRegistrationModel(
    private val firstName: String,
    private val lastName: String,
    private val mobileNumber: String,
    private val companyMobile: String,
    private val email: String,
    private val password: String,
    private val confirmPassword: String,
    private val agreement: Boolean
) : TraderRegistrationContracts.IModel {
    override fun validate(): Boolean =
        firstName.isNotEmpty() && lastName.isNotEmpty() && mobileNumber.isNotEmpty()
                && companyMobile.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
                && password.equals(confirmPassword) && agreement && android.util.Patterns.EMAIL_ADDRESS.matcher(
            email
        ).matches()
                && password.length >= 6
}