package com.squadtechs.markhor.foodapp.trader.activity_trader_main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_dish.TraderFragmentAddDish
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_images.TraderFragmentAddNonFoodImages
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_item.TraderFragmentAddNonFoodItem
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_home.TraderFragmentHome
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_home_non_food.TraderFragmentHomeNonFood
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_profile.TraderFragmentProfile


class ActivityTraderMain : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener, TraderMainCallBack {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentHome: TraderFragmentHome
    private lateinit var fragmentHomeNonFood: TraderFragmentHomeNonFood
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
        fragmentHome = TraderFragmentHome()
        fragmentHomeNonFood = TraderFragmentHomeNonFood()
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

                if (getSharedPreferences(
                        "user_credentials",
                        Context.MODE_PRIVATE
                    ).getString("company_type", "none").equals("Food & beverages")
                ) {
                    changeFragment(fragmentHome)
                } else {
                    changeFragment(fragmentHomeNonFood)
                }

            }
            R.id.item_profile -> {
                changeFragment(TraderFragmentProfile())
            }
            R.id.item_add_item -> {
                val pref = getSharedPreferences("add_item_preferences", Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putBoolean("is_edit", false)
                editor.apply()
                if (getSharedPreferences(
                        "user_credentials",
                        Context.MODE_PRIVATE
                    ).getString("company_type", "none").equals("Food & beverages")
                ) {
                    changeFragment(fragmentAddFood)
                } else {
                    changeFragment(fragmentAddNonFood)
                }
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

    override fun onEditClicked() {
        changeFragment(fragmentAddFood)
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
