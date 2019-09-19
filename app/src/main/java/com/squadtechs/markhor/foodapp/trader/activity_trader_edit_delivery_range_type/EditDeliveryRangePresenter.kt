package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type

class EditDeliveryRangePresenter(private val mView: EditDeliveryRangeContracts.IView) :
    EditDeliveryRangeContracts.IPresenter {
    private lateinit var mModel: EditDeliveryRangeContracts.IModel
    override fun initValidation(
        doYouDeliver: String,
        deliveryTime: String,
        deliveryRange: String,
        pickUpInfo: String
    ) {
        mModel = EditDeliveryRangeModel(doYouDeliver, deliveryTime, deliveryRange, pickUpInfo)
        mView.onValidationResult(mModel.validate())
    }
}