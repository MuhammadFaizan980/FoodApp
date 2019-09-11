package com.squadtechs.markhor.foodapp.customer.activity_customer_main

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me.CustomerFragmentAroundMe
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_cart.CustomerFragmentCart
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_home.CustomerFragmentHome
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_orders.CustomerFragmentOrders
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_profile.CustomerFragmentProfile

class ActivityCustomerMain : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentHome: CustomerFragmentHome
    private lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_main)
        initViews()
        setNavigationListener()
    }

    private fun setNavigationListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initViews() {
        mainLayout = findViewById(R.id.main_layout)
        fragmentHome = CustomerFragmentHome()
        bottomNavigation = findViewById(R.id.bottom_navigation_view)
        changeFragment(CustomerFragmentHome())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_home -> {
                changeFragment(fragmentHome)
            }
            R.id.item_location -> {
                checkPermissions()
            }
            R.id.item_cart -> {
                changeFragment(CustomerFragmentCart())
            }
            R.id.item_orders -> {
                changeFragment(CustomerFragmentOrders())
            }
            R.id.item_profile -> {
                changeFragment(CustomerFragmentProfile())
            }
        }
        return true
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.commit()

    }

    fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            val permissionArray = arrayOfNulls<String>(1)
            permissionArray[0] = android.Manifest.permission.ACCESS_FINE_LOCATION
            ActivityCompat.requestPermissions(this@ActivityCustomerMain, permissionArray, 99)
        } else {
            changeFragment(CustomerFragmentAroundMe())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 99 && grantResults.isNotEmpty()) {
            for (i in grantResults) {
                Log.i("dxdiag", i.toString())
                if (i == PackageManager.PERMISSION_GRANTED) {
                    continue
                } else {
                    Toast.makeText(
                        this,
                        "Permissions are required for the app to work properly",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
            }
            changeFragment(CustomerFragmentAroundMe())
        }
    }

}
