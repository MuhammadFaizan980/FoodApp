package com.squadtechs.markhor.foodapp.customer.activity_customer_add_address

interface CustomerAddAddressMainContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(address: String, defaultString: String)
        fun saveCustomerAddress()
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onSaveAddressResult(status: Boolean)
    }
}