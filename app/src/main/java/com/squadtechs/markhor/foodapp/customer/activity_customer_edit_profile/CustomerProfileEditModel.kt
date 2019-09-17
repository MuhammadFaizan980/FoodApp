package com.squadtechs.markhor.foodapp.customer.activity_customer_edit_profile

class CustomerProfileEditModel(
    private val firstName: String,
    private val lastName: String,
    private val phone: String
) : CustomerProfileEditContracts.IModel {
    override fun validate(): Boolean =
        !firstName.equals("") && !lastName.equals("") && !phone.equals("")
}