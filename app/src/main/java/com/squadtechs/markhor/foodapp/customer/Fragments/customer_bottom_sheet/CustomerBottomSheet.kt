package com.squadtechs.markhor.foodapp.customer.Fragments.customer_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squadtechs.markhor.foodapp.R

class CustomerBottomSheet : BottomSheetDialogFragment() {
    private lateinit var mView: View
    private lateinit var txtIssue: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var txtRateUs: TextView
    private lateinit var btnClose: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_bottom_sheet, container, false)
        iniViews()
        setListeners()
        return mView
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private fun setListeners() {
        txtIssue.setOnClickListener {
            Toast.makeText(activity!!, "Issue solved", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun iniViews() {
        btnClose = mView.findViewById(R.id.btn_close)
        txtIssue = mView.findViewById(R.id.txt_issue_with_order)
        txtFeedback = mView.findViewById(R.id.txt_feedback)
        txtRateUs = mView.findViewById(R.id.txt_rate_us)
    }

}