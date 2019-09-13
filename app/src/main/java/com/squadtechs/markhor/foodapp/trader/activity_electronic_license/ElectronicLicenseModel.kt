package com.squadtechs.markhor.foodapp.trader.activity_electronic_license

import android.net.Uri

class ElectronicLicenseModel(
    private val uri1: Uri?,
    private val uri2: Uri?,
    private val uri3: Uri?
) : ElectronicLicenseContracts.IModel {
    override fun validate(): Boolean = uri1 != null || uri2 != null || uri3 != null
}