package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import com.google.android.gms.maps.GoogleMap

interface AroundMeContracts {
    interface IPresenter {
        fun checkPermissions()
        fun setCurrentLocation(map: GoogleMap)
    }

    interface IView {
        fun onPerssionsResult(status: Boolean)
    }
}