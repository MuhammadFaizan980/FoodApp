package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

interface AroundMeContracts {
    interface IPresenter {
        fun checkPermissions()
    }

    interface IView {
        fun onPerssionsResult(status: Boolean)
    }
}