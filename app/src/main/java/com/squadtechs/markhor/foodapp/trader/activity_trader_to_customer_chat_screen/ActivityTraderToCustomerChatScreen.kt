package com.squadtechs.markhor.foodapp.trader.activity_trader_to_customer_chat_screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_to_trader_chat.CustomerToTraderAdapter
import com.squadtechs.markhor.foodapp.customer.activity_customer_to_trader_chat.CustomerToTraderModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ActivityTraderToCustomerChatScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imgSend: ImageView
    private lateinit var edtMessage: EditText
    private lateinit var list: ArrayList<CustomerToTraderModel>
    private lateinit var adapter: CustomerToTraderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_to_customer_chat_screen)
        initViews()
        fetchData()
        sendData()
        imgSend.setOnClickListener {
            sendData()
        }
    }

    private fun sendData() {
        val dbRef = FirebaseDatabase.getInstance().getReference("companies")
            .child(
                "company${getSharedPreferences(
                    "user_credentials",
                    Context.MODE_PRIVATE
                ).getString("company_id", "no_data")}"
            )
            .child(intent!!.extras!!.getString("customer_uid") as String)

        val message = edtMessage.text.toString().trim()
        if (!message.equals("")) {
            val headerMap = HashMap<String, Any>()
            headerMap["timestamp"] = ServerValue.TIMESTAMP
            headerMap["last_message"] = message
            headerMap["user_name"] = "${getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("first_name", "n/a")} ${getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("last_name", "n/a")}"
            dbRef.updateChildren(headerMap).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val map = HashMap<String, String>()
                    map["message_body"] = message
                    map["message_by"] = FirebaseAuth.getInstance().uid!!
                    val sdf = SimpleDateFormat("dd/MM/YYYY")
                    val date = sdf.format(Date())
                    map["message_date"] = date
                    dbRef.push().setValue(map).addOnCompleteListener { mTask ->
                        if (!mTask.isSuccessful) {
                            Toast.makeText(this, mTask.exception!!.message!!, Toast.LENGTH_LONG)
                                .show()
                        } else {
                            edtMessage.setText("")
                            hideKeyboard()
                        }
                    }
                } else {
                    Toast.makeText(this, task.exception!!.message!!, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun fetchData() {
        val dbRef = FirebaseDatabase.getInstance().getReference("companies")
            .child(
                "company${getSharedPreferences(
                    "user_credentials",
                    Context.MODE_PRIVATE
                ).getString("company_id", "no_data")}"
            )
            .child(intent!!.extras!!.getString("customer_uid") as String)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    list.clear()
                    try {
                        for (i in p0.children) {
                            val obj: CustomerToTraderModel =
                                i.getValue(CustomerToTraderModel::class.java)!!
                            obj.node_key = i.key!!
                            list.add(obj)
                            adapter.notifyDataSetChanged()
                            recyclerView.scrollToPosition((list.size - 1))
                        }
                    } catch (exc: Exception) {
                    }
                }
            }
        })

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recycler_chat)
        imgSend = findViewById(R.id.img_send)
        edtMessage = findViewById(R.id.edt_message)
        list = ArrayList()
        adapter = CustomerToTraderAdapter(list, this)
    }

    private fun hideKeyboard() {
        val inputManager =
            this@ActivityTraderToCustomerChatScreen.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val focusedView = this@ActivityTraderToCustomerChatScreen.currentFocus
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(
                focusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

}
