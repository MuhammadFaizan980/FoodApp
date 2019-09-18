package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_profile


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
import com.squadtechs.markhor.foodapp.customer.activity_customer_address.ActivityCustomerAddress
import com.squadtechs.markhor.foodapp.customer.activity_customer_change_password.ActivityCustomerChangePassword
import com.squadtechs.markhor.foodapp.customer.activity_customer_edit_profile.ActivityCustomerEditProfile
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class CustomerFragmentProfile : Fragment() {

    private lateinit var mView: View
    private lateinit var txtMyDetails: TextView
    private lateinit var txtChangePassword: TextView
    private lateinit var txtAddress: TextView
    private lateinit var txtSignOut: TextView
    private lateinit var txtUserName: TextView
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.customer_fragment_profile, container, false)
        initViews()
        populateUserName()
        setListeners()
        return mView
    }

    private fun setListeners() {
        txtMyDetails.setOnClickListener {
            startActivity(Intent(activity!!, ActivityCustomerEditProfile::class.java))
            activity!!.finish()
        }

        txtChangePassword.setOnClickListener {
            startActivity(Intent(activity!!, ActivityCustomerChangePassword::class.java))
            activity!!.finish()
        }

        txtAddress.setOnClickListener {
            startActivity(Intent(activity!!, ActivityCustomerAddress::class.java))
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
                editor.putBoolean("customer_logged_in", false)
                editor.apply()
                startActivity(Intent(activity!!, ActivityCustomerLogin::class.java))
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
        txtMyDetails = mView.findViewById(R.id.txt_customer_profile)
        txtChangePassword = mView.findViewById(R.id.txt_customer_change_password)
        txtSignOut = mView.findViewById(R.id.txt_customer_sign_out)
        txtUserName = mView.findViewById(R.id.txt_customer_name)
        txtAddress = mView.findViewById(R.id.txt_customer_address)
        pref = activity!!.applicationContext.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        )
        editor = pref.edit()
    }

}
