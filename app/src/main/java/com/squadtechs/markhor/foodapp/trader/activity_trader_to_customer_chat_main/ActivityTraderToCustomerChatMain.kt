package com.squadtechs.markhor.foodapp.trader.activity_trader_to_customer_chat_main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squadtechs.markhor.foodapp.R

class ActivityTraderToCustomerChatMain : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TraderToCustomerMainAdapter
    private lateinit var list: ArrayList<TraderToCustomerMainModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_to_customer_chat_main)
        initViews()
        fetchData()
    }

    private fun fetchData() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val dbRef = FirebaseDatabase.getInstance().getReference("companies").child(
            "company${getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("company_id", "n/a")}"
        )

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    list.clear()
                    try {
                        for (i in p0.children) {
                            val obj: TraderToCustomerMainModel =
                                i.getValue(TraderToCustomerMainModel::class.java)!!
                            obj.uid = i.key!!
                            list.add(obj)
                            adapter.notifyDataSetChanged()
                        }
                    } catch (exc: Exception) {
                    }
                }
            }
        })
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recycler_trader_chat_main)
        list = ArrayList()
        adapter = TraderToCustomerMainAdapter(list, this)
    }

}