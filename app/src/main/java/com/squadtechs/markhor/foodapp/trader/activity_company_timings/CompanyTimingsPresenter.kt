package com.squadtechs.markhor.foodapp.trader.activity_company_timings

import android.content.Context

class CompanyTimingsPresenter constructor(
    private val mView: CompanyTimingsContracts.IView,
    private val context: Context
) : CompanyTimingsContracts.IPresenter {

    private lateinit var mModel: CompanyTimingsContracts.IModel
    private lateinit var sundayStart: String
    private lateinit var sundayEnd: String
    private lateinit var mondayStart: String
    private lateinit var mondayEnd: String
    private lateinit var tuesdayStart: String
    private lateinit var tuesdayEnd: String
    private lateinit var wednesdayStart: String
    private lateinit var wednesdayEnd: String
    private lateinit var thursdayStart: String
    private lateinit var thursdayEnd: String
    private lateinit var fridayStart: String
    private lateinit var fridayEnd: String
    private lateinit var saturdayStart: String
    private lateinit var saturdayEnd: String
    private lateinit var location: String

    override fun initValidation(
        sundayOpen: String,
        sundayClose: String,
        mondayOpen: String,
        mondayClose: String,
        tuesdayOpen: String,
        tuesdayClose: String,
        wednesdayOpen: String,
        wednesdayClose: String,
        thursdayOpen: String,
        thursdayClose: String,
        fridayOpen: String,
        fridayClose: String,
        saturdayOpen: String,
        saturdayClose: String,
        location: String
    ) {
        sundayStart = sundayOpen
        sundayEnd = sundayClose
        mondayStart = mondayOpen
        mondayEnd = mondayClose
        tuesdayStart = tuesdayOpen
        tuesdayEnd = tuesdayClose
        wednesdayStart = wednesdayOpen
        wednesdayEnd = wednesdayClose
        thursdayStart = thursdayOpen
        thursdayEnd = thursdayClose
        fridayStart = fridayOpen
        fridayEnd = fridayClose
        saturdayStart = saturdayOpen
        saturdayEnd = saturdayClose
        this.location = location
        mModel = CompanyTimingsModel(
            sundayOpen,
            sundayClose,
            mondayOpen,
            mondayClose,
            tuesdayOpen,
            tuesdayClose,
            wednesdayOpen,
            wednesdayClose,
            thursdayOpen,
            thursdayClose,
            fridayOpen,
            fridayClose,
            saturdayOpen,
            saturdayClose,
            location
        )
        mView.onValidationResult(mModel.validate())
    }

    override fun addCompanyTimings() {
        TODO("not implemented")
    }

    override fun getLocation() {
        TODO("not implemented")
    }

}