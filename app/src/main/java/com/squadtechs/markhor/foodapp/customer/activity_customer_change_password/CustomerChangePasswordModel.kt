package com.squadtechs.markhor.foodapp.customer.activity_customer_change_password

class CustomerChangePasswordModel(
    private val currentPassword: String,
    private val currentSavedPassword: String,
    private val newPassword: String,
    private val confirmNewPassword: String
) : CustomerChangePasswordMainContracts.IModel {
    override fun validate(): Boolean =
        currentPassword.equals(currentSavedPassword) && !newPassword.equals("")
                && newPassword.equals(confirmNewPassword) && newPassword.length >= 6
}