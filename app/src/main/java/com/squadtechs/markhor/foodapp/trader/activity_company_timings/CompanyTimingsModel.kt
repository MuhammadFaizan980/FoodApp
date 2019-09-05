package com.squadtechs.markhor.foodapp.trader.activity_company_timings

class CompanyTimingsModel(
    private val sundayOpen: String,
    private val sundayClose: String,
    private val mondayOpen: String,
    private val mondayClose: String,
    private val tuesdayOpen: String,
    private val tuesdayClose: String,
    private val wednesdayOpen: String,
    private val wednesdayClose: String,
    private val thursdayOpen: String,
    private val thursdayClose: String,
    private val fridayOpen: String,
    private val fridayClose: String,
    private val saturdayOpen: String,
    private val saturdayClose: String,
    private val location: String
) : CompanyTimingsContracts.IModel {

    override fun validate(): Boolean = !sundayOpen.equals("") && !sundayClose.equals("")
            && !mondayOpen.equals("") && !mondayClose.equals("") && !tuesdayOpen.equals("")
            && !tuesdayClose.equals("") && !wednesdayOpen.equals("") && !wednesdayClose.equals("")
            && !thursdayOpen.equals("") && !thursdayClose.equals("") && !fridayOpen.equals("")
            && !fridayClose.equals("") && !saturdayOpen.equals("") && !saturdayClose.equals("")
            && !location.equals("")

}