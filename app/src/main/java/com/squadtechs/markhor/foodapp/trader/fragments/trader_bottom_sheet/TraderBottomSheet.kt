package com.squadtechs.markhor.foodapp.trader.fragments.trader_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squadtechs.markhor.foodapp.R

class TraderBottomSheet : BottomSheetDialogFragment() {
    private lateinit var mView: View
    private lateinit var txtIssue: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var txtRateUs: TextView
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
    }

    private fun iniViews() {
        txtIssue = mView.findViewById(R.id.txt_issue_with_order)
        txtFeedback = mView.findViewById(R.id.txt_feedback)
        txtRateUs = mView.findViewById(R.id.txt_rate_us)
    }

}
