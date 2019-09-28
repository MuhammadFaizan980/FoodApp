package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fargment_review_sheet

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.SingletonQueue
import com.squadtechs.markhor.foodapp.customer.activity_customer_order_details.CustomerOrderDetailsCallback
import org.json.JSONObject


class ReviewBottomSheet(private val companyID: String) : BottomSheetDialogFragment() {
    private lateinit var mView: View
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSubmit: Button
    private lateinit var obj: CustomerOrderDetailsCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.review_bottom_sheet, container, false)
        initViews()
        setRatingListener()
        submitReview()
        return mView
    }

    private fun submitReview() {
        btnSubmit.setOnClickListener {
            val API = "http://squadtechsolution.com/android/v1/add_rating.php"
            val requestQueue = SingletonQueue.getRequestQueue(activity!!)
            val map = HashMap<String, String>()
            map["company_id"] = companyID
            map["rating"] = ratingBar.rating.toString()
            Log.i("faizan", map.toString())
            val stringRequest = object : StringRequest(
                Method.POST,
                API,
                Response.Listener { response ->
                    Log.i("review_response", response)
                    try {
                        if (JSONObject(response).getString("status").equals(" Rating  Successfully")) {
                            Toast.makeText(activity!!, "Review added", Toast.LENGTH_SHORT).show()
                        }
                        dismiss()
                        obj.onReviewAdded()
                    } catch (exc: Exception) {
                        Log.i("review_exception", exc.toString())
                        dismiss()
                    }
                },
                Response.ErrorListener { error ->
                    Log.i("review_response", error.toString())
                    dismiss()
                }) {
                override fun getParams(): MutableMap<String, String> = map
            }
            requestQueue.add(stringRequest)
        }
    }

    private fun setRatingListener() {
        ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if (rating < 1.0f)
                    ratingBar.rating = 1.0f
            }
    }

    private fun initViews() {
        ratingBar = mView.findViewById(R.id.rating_bar)
        btnSubmit = mView.findViewById(R.id.btn_submit)
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        obj = context as CustomerOrderDetailsCallback
    }

}
