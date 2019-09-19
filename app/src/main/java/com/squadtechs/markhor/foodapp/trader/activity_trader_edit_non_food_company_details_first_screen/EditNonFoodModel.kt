package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_non_food_company_details_first_screen

class EditNonFoodModel(
    private val companyName: String,
    private val companyCuisine: String,
    private val companyPhone: String,
    private val companyDescription: String
) : EditNonFoodContracts.IModel {
    override fun validate(): Boolean =
        !companyName.equals("") && !companyCuisine.equals("") && !companyPhone.equals("")
                && !companyDescription.equals("")
}