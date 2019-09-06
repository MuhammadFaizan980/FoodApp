package com.squadtechs.markhor.foodapp.trader.activity_delivery_details

class DeliveryDetailsModel(
    private val deliver: Boolean,
    private val range: String,
    private val pickupInformation: String
) : DeliveryDetailsContracts.IModel {
    override fun validate(): Boolean =
        deliver && !range.equals("") && !range.equals("0") && !pickupInformation.equals("")
}