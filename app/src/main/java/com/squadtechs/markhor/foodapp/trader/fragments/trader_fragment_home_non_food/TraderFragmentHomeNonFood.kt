package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_home_non_food

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import com.squadtechs.markhor.foodapp.trader.activity_trader_to_customer_chat_main.ActivityTraderToCustomerChatMain

class TraderFragmentHomeNonFood : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TraderNonFoodAdapter
    private lateinit var imgChat: ImageView
    private lateinit var list: ArrayList<TraderNonFoodModel>
    private lateinit var txtNewMessage: TextView
    private var serverTimeValue: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_home_non_food, container, false)
        initViews()
        getChatCount()
        getData()

        imgChat.setOnClickListener {
            val editor =
                activity!!.getSharedPreferences("trader_time_stamp", Context.MODE_PRIVATE).edit()
            editor.putLong("value", serverTimeValue)
            editor.apply()
            takeUserToChatScreen()
        }
        return mView
    }

    private fun getData() {
        val pd = MainUtils.getLoadingDialog(activity!!, "Loading", "Please wait", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/displayAllNonFood.php"
        val requestQueue = Volley.newRequestQueue(activity!!)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("non_food_response", response)
                try {
                    val type = object : TypeToken<ArrayList<TraderNonFoodModel>>() {}.type
                    list = Gson().fromJson(response, type)

                    val trader_id =
                        activity!!.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                            .getString("id", "none")
                    var count = (list.size - 1)
                    while (count >= 0) {
                        if (!list[count].trader_id.equals(trader_id)) {
                            list.removeAt(count)
                        }
                        count--
                    }
                    recyclerView.layoutManager = GridLayoutManager(activity!!, 2)
                    adapter = TraderNonFoodAdapter(list, activity!!)
                    recyclerView.adapter = adapter

                } catch (exc: Exception) {
                    Log.i("non_food_exception", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Log.i("non_food_response", error.toString())
            })
        requestQueue.add(stringRequest)
    }

    private fun initViews() {
        txtNewMessage = mView.findViewById(R.id.new_message_badge)
        imgChat = mView.findViewById(R.id.img_chat)
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

    private fun getChatCount() {
        val dbRef = FirebaseDatabase.getInstance().getReference("companies").child(
            "company${activity!!.getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("company_id", "none")}"
        ).child("timestamp")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                try {
                    if (p0.exists()) {
                        serverTimeValue = p0.value as Long
                        if (serverTimeValue > activity!!.getSharedPreferences(
                                "trader_time_stamp",
                                Context.MODE_PRIVATE
                            ).getLong("value", 0)
                        ) {
                            txtNewMessage.visibility = View.VISIBLE
                        }
                    }
                } catch (exc: Exception) {

                }
            }
        })
    }

    private fun takeUserToChatScreen() {
        val mIntent = Intent(activity!!, ActivityTraderToCustomerChatMain::class.java)
        mIntent.putExtra(
            "company_id",
            activity!!.getSharedPreferences(
                "user_credentials",
                Context.MODE_PRIVATE
            ).getString("company_id", "n/a") as String
        )
        startActivity(mIntent)
        activity!!.finish()
    }


}
