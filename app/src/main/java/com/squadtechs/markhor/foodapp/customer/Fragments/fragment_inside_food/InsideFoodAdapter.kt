package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squadtechs.markhor.foodapp.R
import com.squareup.picasso.Picasso

class InsideFoodAdapter(
    private val list: ArrayList<InsIdeFoodModel>,
    private val context: Context,
    private val tabPosition: Int
) : RecyclerView.Adapter<InsideFoodAdapter.FoodHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        if (tabPosition == 1) {
            return FoodHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.cuisine_row_design,
                    parent,
                    false
                )
            )
        } else {
            return FoodHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.other_food_row_design,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val obj = list[position]
        if (tabPosition == 1) {
            Picasso.get().load("http://squadtechsolution.com/android/v1/${obj.company_logo}")
                .into(holder.imageView)
            holder.txtCuisine.text = obj.cuisine
        } else {
            Picasso.get().load("http://squadtechsolution.com/android/v1/${obj.company_logo}")
                .into(holder.imageView)
            holder.txtTitle.text = obj.company_name
            holder.txtCuisine.text = obj.cuisine
            holder.txtDeliveryType.text = obj.delivery_type
        }
    }

    inner class FoodHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var imageView: RoundedImageView
        lateinit var txtTitle: TextView
        lateinit var txtCuisine: TextView
        lateinit var txtReviewCount: TextView
        lateinit var txtTime: TextView
        lateinit var txtDeliveryType: TextView
        lateinit var rattingBar: RatingBar

        init {
            if (tabPosition == 1) {
                imageView = view.findViewById(R.id.img_cuisine)
                txtCuisine = view.findViewById(R.id.txt_cuisine)
            } else {
                imageView = view.findViewById(R.id.img_company)
                txtTitle = view.findViewById(R.id.txt_title)
                txtCuisine = view.findViewById(R.id.txt_cuisine)
                txtReviewCount = view.findViewById(R.id.txt_review_count)
                txtTime = view.findViewById(R.id.txt_time)
                txtDeliveryType = view.findViewById(R.id.txt_delivery)
                rattingBar = view.findViewById(R.id.ratting_bar)
            }
        }

    }

}