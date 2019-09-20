package com.squadtechs.markhor.foodapp.trader.activity_trader_to_customer_chat_main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_to_customer_chat_screen.ActivityTraderToCustomerChatScreen

class TraderToCustomerMainAdapter(
    private val list: ArrayList<TraderToCustomerMainModel>,
    private val context: Context
) : RecyclerView.Adapter<TraderToCustomerMainAdapter.TraderToCustomerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraderToCustomerHolder =
        TraderToCustomerHolder(
            LayoutInflater.from(context).inflate(
                R.layout.trader_chat_main_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: TraderToCustomerHolder, position: Int) {
        val obj = list[position]
        holder.txtUserName.text = obj.user_name
        holder.txtUserLastMessage.text = obj.last_message
        holder.touchView.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    ActivityTraderToCustomerChatScreen::class.java
                ).putExtra("customer_uid", obj.uid)
            )
        }
    }

    inner class TraderToCustomerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUserName: TextView = view.findViewById(R.id.txt_user_name)
        val txtUserLastMessage: TextView = view.findViewById(R.id.txt_user_last_message)
        val touchView: View = view.findViewById(R.id.touch_view)
    }

}