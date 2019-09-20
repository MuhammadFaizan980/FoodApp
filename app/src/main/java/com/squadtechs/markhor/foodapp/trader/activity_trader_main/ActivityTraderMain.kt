package com.squadtechs.markhor.foodapp.trader.activity_trader_main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_dish.TraderFragmentAddDish
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_images.TraderFragmentAddNonFoodImages
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_item.TraderFragmentAddNonFoodItem
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_home.TraderFargmentHome
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_profile.TraderFragmentProfile
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class ActivityTraderMain : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener, TraderMainCallBack {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentHome: TraderFargmentHome
    private lateinit var fragmentProfile: TraderFragmentProfile
    private lateinit var fragmentAddFood: TraderFragmentAddDish
    private lateinit var fragmentAddNonFood: TraderFragmentAddNonFoodItem
    private lateinit var fragmentAddNonFoodImages: TraderFragmentAddNonFoodImages
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_main)
        checkPermissions()
        initViews()
        setNavigationListener()
    }

    private fun setNavigationListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }


    private fun initViews() {
        fragmentHome = TraderFargmentHome()
        fragmentProfile = TraderFragmentProfile()
        fragmentAddFood = TraderFragmentAddDish()
        fragmentAddNonFood = TraderFragmentAddNonFoodItem()
        fragmentAddNonFoodImages = TraderFragmentAddNonFoodImages()
        bottomNavigation = findViewById(R.id.bottom_navigation_view)
        changeFragment(fragmentHome)
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        currentFragment = fragment
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_home -> {
                changeFragment(fragmentHome)
            }
            R.id.item_profile -> {
                changeFragment(TraderFragmentProfile())
            }
            R.id.item_add_item -> {
                changeFragment(fragmentAddNonFood)
            }
        }
        return true
    }

    override fun onFragmentTap(key: String) {
        if (key.equals("images")) {
            changeFragment(fragmentAddNonFoodImages)
        } else {
            changeFragment(fragmentAddNonFood)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        finish()
    }

    fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            val permissionArray = arrayOfNulls<String>(1)
            permissionArray[0] = android.Manifest.permission.READ_EXTERNAL_STORAGE
            ActivityCompat.requestPermissions(this@ActivityTraderMain, permissionArray, 99)
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

}
