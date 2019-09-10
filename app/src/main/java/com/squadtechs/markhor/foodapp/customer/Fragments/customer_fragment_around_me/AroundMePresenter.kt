package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AroundMePresenter(private val mView: AroundMeContracts.IView, private val context: Context, private val mActivity: Activity) :
    AroundMeContracts.IPresenter {
    override fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
            || ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            val permissionArray = arrayOfNulls<String>(2)
            permissionArray[0] = android.Manifest.permission.ACCESS_FINE_LOCATION
            permissionArray[1] = android.Manifest.permission.ACCESS_COARSE_LOCATION
            ActivityCompat.requestPermissions(mActivity, permissionArray, 99)
            mView.onPerssionsResult(false)
        } else {
            mView.onPerssionsResult(true)
        }
    }
}