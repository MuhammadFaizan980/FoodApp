package com.squadtechs.markhor.foodapp.customer.activity_customer_to_trader_chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.squadtechs.markhor.foodapp.R

class CustomerToTraderAdapter(
    private val list: ArrayList<CustomerToTraderModel>,
    private val context: Context
) : RecyclerView.Adapter<CustomerToTraderAdapter.CustomerToTraderHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerToTraderHolder =
        CustomerToTraderHolder(
            LayoutInflater.from(context).inflate(
                R.layout.chat_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: CustomerToTraderHolder, position: Int) {
        val obj = list[position]
        if (!obj.message_by.equals(FirebaseAuth.getInstance().uid!!)) {
            holder.linearCustomerChat.visibility = View.INVISIBLE
            holder.linearTraderChat.visibility = View.VISIBLE
            holder.txtTraderMessage.text = obj.message_body
            holder.txtTraderMessageDate.text = obj.message_date
        } else {
            holder.linearCustomerChat.visibility = View.VISIBLE
            holder.linearTraderChat.visibility = View.INVISIBLE
            holder.txtCustomerMessage.text = obj.message_body
            holder.txtCustomerMessageDate.text = obj.message_date
        }
    }

    inner class CustomerToTraderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linearCustomerChat: LinearLayout = view.findViewById(R.id.linear_chat_customer)
        val txtCustomerMessage: TextView = view.findViewById(R.id.txt_customer_message)
        val txtCustomerMessageDate: TextView = view.findViewById(R.id.txt_customer_message_date)
        val linearTraderChat: LinearLayout = view.findViewById(R.id.linear_chat_trader)
        val txtTraderMessage: TextView = view.findViewById(R.id.txt_trader_message)
        val txtTraderMessageDate: TextView = view.findViewById(R.id.txt_trader_message_date)
    }

}