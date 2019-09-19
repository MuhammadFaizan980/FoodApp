package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_non_food_company_details_first_screen

class EditNonFoodPresenter(private val mView: EditNonFoodContracts.IView) :
    EditNonFoodContracts.IPresenter {
    private lateinit var mModel: EditNonFoodContracts.IModel

    override fun initValidation(
        companyName: String,
        companyPhone: String,
        cuisine: String,
        companyDescription: String
    ) {
        mModel = EditNonFoodModel(companyName, cuisine, companyPhone, companyDescription)
        mView.onValidationResult(mModel.validate())
    }
}