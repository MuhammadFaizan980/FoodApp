package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food

import android.location.Location

interface InsideFoodContracts {
        fun onGetLocationResponse(status: Boolean, location: Location?)
}