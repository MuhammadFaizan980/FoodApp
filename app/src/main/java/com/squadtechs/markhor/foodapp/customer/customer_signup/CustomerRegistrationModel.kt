package com.squadtechs.markhor.foodapp.customer.customer_signup

class CustomerRegistrationModel(
    private val firstName: String, private val lastName: String, private val mobileNumber: String,
    private val birthday: String, private val email: String,
    private val password: String, private val confirmPassword: String, private val userAgreement: Boolean
) : CustomerRegistrationContracts.IModel {
    override fun validate(): Boolean = firstName.isNotEmpty() && lastName.isNotEmpty() && mobileNumber.isNotEmpty()
            && birthday.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
            && password.equals(confirmPassword) && userAgreement && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}