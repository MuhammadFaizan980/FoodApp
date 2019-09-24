package com.squadtechs.markhor.foodapp.customer.activity_customer_main

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me.CustomerFragmentAroundMe
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_cart.CustomerFragmentCart
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_food_companies.CustomerFragmentFoodCompanies
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_home.CustomerFragmentHome
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_orders.CustomerFragmentOrders
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_profile.CustomerFragmentProfile
import com.squadtechs.markhor.foodapp.customer.db_utiils.DbUtils

class ActivityCustomerMain : AppCompatActivity(), CustomerFoodFragmetnCallback {
    //  private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentHome: CustomerFragmentHome
    private var isInFoodCompanies: Boolean = false
    private lateinit var item1: AHBottomNavigationItem
    private lateinit var item2: AHBottomNavigationItem
    private lateinit var item3: AHBottomNavigationItem
    private lateinit var item4: AHBottomNavigationItem
    private lateinit var item5: AHBottomNavigationItem
    private lateinit var bottomNavigation: AHBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_main)
        checkPermissions()
        createBottomNav()
        initViews()
        //       setNavigationListener()
    }

//    private fun setNavigationListener() {
//        bottomNavigation.setOnNavigationItemSelectedListener(this)
//    }

    private fun initViews() {
        fragmentHome = CustomerFragmentHome()
//        bottomNavigation = findViewById(R.id.bottom_navigation_view)
        changeFragment(CustomerFragmentHome())
    }

    private fun createBottomNav() {
        bottomNavigation = findViewById(R.id.bottom_navigation)
        item1 =
            AHBottomNavigationItem(R.string.home, R.drawable.ic_home, R.color.colorBlack)
        item2 =
            AHBottomNavigationItem(R.string.near_me, R.drawable.ic_location, R.color.colorBlack)
        item3 =
            AHBottomNavigationItem(R.string.cart, R.drawable.ic_cart, R.color.colorBlack)
        item4 =
            AHBottomNavigationItem(R.string.my_orders, R.drawable.ic_notes, R.color.colorBlack)
        item5 =
            AHBottomNavigationItem(R.string.profile, R.drawable.ic_person, R.color.colorBlack)
        bottomNavigation.addItem(item1)
        bottomNavigation.addItem(item2)
        bottomNavigation.addItem(item3)
        bottomNavigation.addItem(item4)
        bottomNavigation.addItem(item5)
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW)
        bottomNavigation.setForceTint(true)
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"))
        bottomNavigation.setAccentColor(Color.parseColor("#FF7132"))

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            when (position) {
                0 -> {
                    changeFragment(fragmentHome)
                }
                1 -> {
                    changeFragment(CustomerFragmentAroundMe())
                }
                2 -> {
                    changeFragment(CustomerFragmentCart())
                }
                3 -> {
                    changeFragment(CustomerFragmentOrders())
                }
                4 -> {
                    changeFragment(CustomerFragmentProfile())
                }
            }
            true
        }

    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.item_home -> {
//                changeFragment(fragmentHome)
//            }
//            R.id.item_location -> {
//                changeFragment(CustomerFragmentAroundMe())
//            }
//            R.id.item_cart -> {
//                changeFragment(CustomerFragmentCart())
//            }
//            R.id.item_orders -> {
//                changeFragment(CustomerFragmentOrders())
//            }
//            R.id.item_profile -> {
//                changeFragment(CustomerFragmentProfile())
//            }
//        }
//        return true
//    }

    private fun changeFragment(fragment: Fragment) {
        val db = DbUtils(this)
        val cartCount = db.getTotalCount()
        if (cartCount > 0) {
            bottomNavigation.setNotification(cartCount.toString(), 2)
        } else {
            bottomNavigation.setNotification("", 2)
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.addToBackStack(null)
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
            return
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
        }
    }

    override fun onFoodSelected() {
        isInFoodCompanies = true
        changeFragment(CustomerFragmentFoodCompanies())
    }

    override fun onBackPressed() {
        if (isInFoodCompanies) {
            isInFoodCompanies = false
            changeFragment(fragmentHome)
        } else {
            finish()
        }
    }

}
