package com.squadtechs.markhor.foodapp.customer.activity_customer_select_address

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R

class SelectAdapter(
    private val list: ArrayList<SelectModel>,
    private val context: Context,
    private val mView: SelectContract
) : RecyclerView.Adapter<SelectAdapter.AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder =
        AddressViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.select_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.txtAddress.text = list[position].address
        holder.txtSelectAddress.setOnClickListener {
            mView.addressSelected(list[position].address)
        }
    }

    inner class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtSelectAddress: TextView = view.findViewById(R.id.txt_select_address)
        val txtAddress: TextView = view.findViewById(R.id.txt_address)
    }
}