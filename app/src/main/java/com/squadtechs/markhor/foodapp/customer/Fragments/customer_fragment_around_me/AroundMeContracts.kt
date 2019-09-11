package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import com.google.android.gms.maps.GoogleMap

interface AroundMeContracts {
    interface IPresenter {
        fun setCurrentLocation(map: GoogleMap)
        fun fetchHttpData()
        fun setMarkers(map: GoogleMap, distance: Int)
    }

    interface IView {
        fun setCurrentLocationResult(status: Boolean)
        fun onFetchHttpDataResult(status: Boolean)
    }
}