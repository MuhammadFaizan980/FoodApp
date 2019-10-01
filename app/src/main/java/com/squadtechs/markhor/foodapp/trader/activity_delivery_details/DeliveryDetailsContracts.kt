package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

interface DeliveryDetailsContracts {
    interface IModel {
        fun validate()
    }

    interface IPresenter {
        fun validationCallBack(status: Boolean, message: String)
        fun initValidation(
            deliver: Boolean,
            range: String,
            pickupInformation: String,
            deliveryTime: String
        )

        fun addDeliveryDetails()
    }

    interface IView {
        fun onValidationResult(status: Boolean, message: String)
        fun onAddDeliveryDetailsResult(status: Boolean)
    }
}