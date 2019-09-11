package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import com.google.android.gms.maps.GoogleMap

interface AroundMeContracts {
    interface IPresenter {
        fun setCurrentLocation(map: GoogleMap)
    }

    interface IView {
    }
}