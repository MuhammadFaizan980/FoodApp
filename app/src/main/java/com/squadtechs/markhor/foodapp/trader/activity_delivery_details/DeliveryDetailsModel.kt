package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

class DeliveryDetailsModel(
    private val mPresenter: DeliveryDetailsContracts.IPresenter,
    private val deliver: Boolean,
    private val deliveryTime: String,
    private val range: String,
    private val pickupInformation: String
) : DeliveryDetailsContracts.IModel {
    override fun validate() {
        if (deliver) {
            if (deliveryTime.isEmpty()) {
                mPresenter.validationCallBack(false, "Delivery time cannot be empty")
            } else if (range.equals("0")) {
                mPresenter.validationCallBack(false, "Delivery range cannot be set to 0")
            } else if (pickupInformation.isEmpty()) {
                mPresenter.validationCallBack(false, "Pickup information cannot be empty")
            } else {
                mPresenter.validationCallBack(true, "none")
            }
        } else {
            if (pickupInformation.isEmpty()) {
                mPresenter.validationCallBack(false, "Pickup information cannot be empty")
            } else {
                mPresenter.validationCallBack(true, "none")
            }
        }
    }
}