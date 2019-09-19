package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_food_company_details_first_screen

class EditFoodPresenter(private val mView: TraderEditFoodCompanyFirstScreenMainContracts.IView) :
    TraderEditFoodCompanyFirstScreenMainContracts.IPresenter {

    private lateinit var mModel: TraderEditFoodCompanyFirstScreenMainContracts.IModel

    override fun initValidation(
        companyName: String,
        companyPhone: String,
        cuisine: String,
        companyDescription: String
    ) {
        mModel = EditFoodModel(companyName, cuisine, companyPhone, companyDescription)
        mView.onValidationResult(mModel.validate())
    }
}