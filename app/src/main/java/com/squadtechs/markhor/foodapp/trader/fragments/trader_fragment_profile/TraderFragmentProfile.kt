package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import com.squadtechs.markhor.foodapp.trader.activity_trader_change_password.ActivityTraderChangePassword
import com.squadtechs.markhor.foodapp.trader.trader_login.ActivityTraderLogin

class TraderFragmentProfile : Fragment() {

    private lateinit var mView: View
    private lateinit var txtMyDetails: TextView
    private lateinit var txtChangePassword: TextView
    private lateinit var txtSignOut: TextView
    private lateinit var txtUserName: TextView
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_profile, container, false)
        initViews()
        populateUserName()
        setListeners()
        return mView
    }

    private fun setListeners() {
        txtMyDetails.setOnClickListener {
        }

        txtChangePassword.setOnClickListener {
            startActivity(Intent(activity!!, ActivityTraderChangePassword::class.java))
            activity!!.finish()
        }

        txtSignOut.setOnClickListener {
            val dialog = MainUtils.getMessageDialog(
                activity!!,
                "Message!",
                "Are you sure you want to log out?",
                false
            )
            dialog.setPositiveButton("Log out") { dialogInterface, i ->
                editor.putBoolean("trader_logged_in", false)
                editor.apply()
                startActivity(Intent(activity!!, ActivityTraderLogin::class.java))
                activity!!.finish()
            }
                .setNegativeButton("Cancel") { dialogInterface, i ->
                    dialogInterface.cancel()
                }
            dialog.show()
        }

    }

    private fun populateUserName() {
        val userName = pref.getString("last_name", "n/a")
        txtUserName.text = "Hello $userName!"
    }

    private fun initViews() {
        txtMyDetails = mView.findViewById(R.id.txt_trader_profile)
        txtChangePassword = mView.findViewById(R.id.txt_trader_change_password)
        txtSignOut = mView.findViewById(R.id.txt_trader_sign_out)
        txtUserName = mView.findViewById(R.id.txt_trader_name)
        pref = activity!!.applicationContext.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        )
        editor = pref.edit()
    }

}
