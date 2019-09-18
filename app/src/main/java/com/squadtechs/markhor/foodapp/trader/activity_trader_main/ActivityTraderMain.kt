package com.squadtechs.markhor.foodapp.trader.activity_trader_main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_home.TraderFargmentHome
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_profile.TraderFragmentProfile

class ActivityTraderMain : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentHome: TraderFargmentHome
    private lateinit var fragmentProfile: TraderFragmentProfile
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_main)
        initViews()
        setNavigationListener()
    }

    private fun setNavigationListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }


    private fun initViews() {
        fragmentHome = TraderFargmentHome()
        fragmentProfile = TraderFragmentProfile()
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
        }
        return true
    }

    override fun onBackPressed() {
        finish()
    }

}
