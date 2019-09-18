package com.squadtechs.markhor.foodapp.customer.activity_customer_add_address

class CustomerAddAddressModel (private val address: String): CustomerAddAddressMainContracts.IModel {
    override fun validate(): Boolean = !address.equals("")
}