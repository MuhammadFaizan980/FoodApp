package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies.ActivityCustomerNonFoodCompanies

class CustomerFragmentHomeAdapter(
    private val context: Context,
    private val list: ArrayList<String>,
    private val mActivity: Activity
) : RecyclerView.Adapter<CustomerFragmentHomeAdapter.CustomerFragmentHomeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerFragmentHomeViewHolder = CustomerFragmentHomeViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.customer_home_row_design,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: CustomerFragmentHomeViewHolder, position: Int) {
        holder.txtTitle.text = list[position]
        when (position) {
            0 -> {
                holder.imgCategory.setImageResource(R.drawable.clothes)
            }
            1 -> {
                holder.imgCategory.setImageResource(R.drawable.accessories)
            }
            2 -> {
                holder.imgCategory.setImageResource(R.drawable.skincare)
            }
            3 -> {
                holder.imgCategory.setImageResource(R.drawable.homeware)
            }
            4 -> {
                holder.imgCategory.setImageResource(R.drawable.toys)
            }
            5 -> {
                holder.imgCategory.setImageResource(R.drawable.shoes)
            }
            6 -> {
                holder.imgCategory.setImageResource(R.drawable.bags)
            }
            7 -> {
                holder.imgCategory.setImageResource(R.drawable.others)
            }
        }

        holder.touchView.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    ActivityCustomerNonFoodCompanies::class.java
                ).putExtra("position", position)
            )
            mActivity.finish()
        }

    }

    inner class CustomerFragmentHomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCategory: RoundedImageView = view.findViewById(R.id.img_category)
        val txtTitle: TextView = view.findViewById(R.id.txt_item_title)
        val touchView: View = view.findViewById(R.id.touch_view)
    }
}