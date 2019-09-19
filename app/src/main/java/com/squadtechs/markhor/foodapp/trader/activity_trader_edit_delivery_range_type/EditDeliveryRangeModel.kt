package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type

class EditDeliveryRangeModel(
    private val doYouDeliver: String,
    private val deliveryTime: String,
    private val deliveryRange: String,
    private val pickUpInfo: String
) : EditDeliveryRangeContracts.IModel {
    override fun validate(): Boolean {
        if (doYouDeliver.equals("yes")) {
            return !deliveryTime.equals("") && !deliveryRange.equals("0") && !pickUpInfo.equals("")
        } else {
            return !pickUpInfo.equals("")
        }
    }
}