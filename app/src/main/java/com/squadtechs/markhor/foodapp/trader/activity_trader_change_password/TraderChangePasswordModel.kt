package com.squadtechs.markhor.foodapp.trader.activity_trader_change_password

class TraderChangePasswordModel(
    private val currentPassword: String,
    private val currentSavedPassword: String,
    private val newPassword: String,
    private val confirmNewPassword: String
) : TraderChangePasswordMainContracts.IModel {
    override fun validate(): Boolean =
        currentPassword.equals(currentSavedPassword) && !newPassword.equals("")
                && newPassword.equals(confirmNewPassword) && newPassword.length >= 6
}