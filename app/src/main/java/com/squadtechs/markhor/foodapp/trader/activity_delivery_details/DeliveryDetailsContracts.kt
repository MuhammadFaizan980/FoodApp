package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

interface DeliveryDetailsContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(
            deliver: Boolean,
            range: String,
            pickupInformation: String,
            deliveryTime: String
        )

        fun addDeliveryDetails()
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onAddDeliveryDetailsResult(status: Boolean)
    }
}