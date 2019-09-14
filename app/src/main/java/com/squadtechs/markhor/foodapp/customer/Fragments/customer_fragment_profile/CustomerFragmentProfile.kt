package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_edit_profile.ActivityCustomerEditProfile

/**
 * A simple [Fragment] subclass.
 */
class CustomerFragmentProfile : Fragment() {

    private lateinit var mView: View
    private lateinit var txtMyDetails: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.customer_fragment_profile, container, false)
        initViews()
        setListeners()
        return mView
    }

    private fun setListeners() {
        txtMyDetails.setOnClickListener {
            startActivity(Intent(activity!!, ActivityCustomerEditProfile::class.java))
            activity!!.finish()
        }
    }

    private fun initViews() {
        txtMyDetails = mView.findViewById(R.id.txt_customer_profile)
    }

}
