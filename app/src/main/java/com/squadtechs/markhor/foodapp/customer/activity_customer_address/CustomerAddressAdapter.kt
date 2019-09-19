package com.squadtechs.markhor.foodapp.customer.activity_customer_address

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R

class CustomerAddressAdapter(
    private val list: ArrayList<CustomerAddressModel>,
    private val context: Context
) : RecyclerView.Adapter<CustomerAddressAdapter.AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder =
        AddressViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.address_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val obj = list[position]
        holder.txtAddress.text = obj.address
        if (obj.is_default.equals("no") || obj.is_default.equals("No")) {
            holder.txtDefaultAddress.visibility = View.INVISIBLE
        }
    }

    inner class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtEdit: TextView = view.findViewById(R.id.txt_edit_address)
        val txtAddress: TextView = view.findViewById(R.id.txt_address)
        val txtDefaultAddress: TextView = view.findViewById(R.id.txt_default_address)
    }
}