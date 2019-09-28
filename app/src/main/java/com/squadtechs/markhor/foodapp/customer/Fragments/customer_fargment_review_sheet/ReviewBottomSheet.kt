package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fargment_review_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squadtechs.markhor.foodapp.R

class ReviewBottomSheet : BottomSheetDialogFragment() {
    private lateinit var mView: View
    private lateinit var imgClose: ImageView
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSubmit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.review_bottom_sheet, container, false)
        initViews()
        return mView
    }

    private fun initViews() {
        imgClose = mView.findViewById(R.id.img_close)
        ratingBar = mView.findViewById(R.id.rating_bar)
        btnSubmit = mView.findViewById(R.id.btn_submit)
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

}
