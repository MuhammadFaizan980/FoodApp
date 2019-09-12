package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squadtechs.markhor.foodapp.R
import com.squareup.picasso.Picasso

class InsideFoodAdapter(
    private val list: ArrayList<InsIdeFoodModel>,
    private val context: Context
) : RecyclerView.Adapter<InsideFoodAdapter.FoodHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder =
        FoodHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cuisine_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val obj = list[position]
        Picasso.get().load("http://squadtechsolution.com/android/v1/${obj.company_logo}")
            .into(holder.imageView)
        holder.txtTitle.text = obj.cuisine
    }

    inner class FoodHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: RoundedImageView = view.findViewById(R.id.img_cuisine)
        var txtTitle: TextView = view.findViewById(R.id.txt_cuisine)
    }

}