package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squadtechs.markhor.foodapp.R

class CustomerFragmentHomeAdapter(
    private val context: Context,
    private val list: ArrayList<String>
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
    }

    inner class CustomerFragmentHomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCategory: RoundedImageView = view.findViewById(R.id.img_category)
        val txtTitle: TextView = view.findViewById(R.id.txt_item_title)
        val touchView: View = view.findViewById(R.id.touch_view)
    }
}