package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type

interface EditDeliveryRangeContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(
            doYouDeliver: String,
            deliveryTime: String,
            deliveryRange: String,
            pickUpInfo: String
        )
    }

    interface IView {
        fun onValidationResult(status: Boolean)
    }
}