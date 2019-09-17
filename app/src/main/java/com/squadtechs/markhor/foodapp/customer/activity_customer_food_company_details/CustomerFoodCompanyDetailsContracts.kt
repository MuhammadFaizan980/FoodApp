package com.squadtechs.markhor.foodapp.customer.activity_customer_food_company_details

interface CustomerFoodCompanyDetailsContracts {
    interface IPresenter {
        fun getCompanyData(companyID: String)
    }

    interface IView {
        fun onGetCompanyDataResult(error: Boolean, responseObj: CustomerFoodCompanyDetailsResponseHandler?)
    }

}