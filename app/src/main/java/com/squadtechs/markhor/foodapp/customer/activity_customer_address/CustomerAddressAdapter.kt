package com.squadtechs.markhor.foodapp.customer.activity_customer_address

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_edit_address.ActivityCustomerEditAddress

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
        if (obj.is_default.equals("no") || obj.is_default.equals("Yes")) {
            holder.txtDefaultAddress.visibility = View.VISIBLE
            val pref = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("default_address", obj.address)
            editor.apply()
        }
        holder.txtEdit.setOnClickListener {
            context.startActivity(
                Intent(context, ActivityCustomerEditAddress::class.java).putExtra(
                    "address_id",
                    obj.id
                )
            )
            (context as Activity).finish()
        }
    }

    inner class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtEdit: TextView = view.findViewById(R.id.txt_edit_address)
        val txtAddress: TextView = view.findViewById(R.id.txt_address)
        val txtDefaultAddress: TextView = view.findViewById(R.id.txt_default_address)
    }
}