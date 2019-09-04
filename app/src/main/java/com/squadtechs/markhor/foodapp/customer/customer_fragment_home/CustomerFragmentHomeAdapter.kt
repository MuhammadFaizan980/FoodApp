package com.squadtechs.markhor.foodapp.customer.customer_fragment_home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        //TODO: bind data to your views hereA
    }

    inner class CustomerFragmentHomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //TODO: declare and initialize your views here
    }
}