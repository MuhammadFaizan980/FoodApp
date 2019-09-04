package com.squadtechs.markhor.foodapp.customer.customer_fragment_home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R

class CustomerFragmentHome : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<String>
    private lateinit var adapter: CustomerFragmentHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_fragment_home, container, false)
        initViews(view)
        populateRecyclerView(view)
        return view
    }

    private fun populateRecyclerView(view: View) {
        recyclerView.layoutManager =
            LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recycler_customer_home)
        list = ArrayList()
        list.add("Faizan")
        list.add("Faizan")
        list.add("Faizan")
        list.add("Faizan")
        list.add("Faizan")
        adapter = CustomerFragmentHomeAdapter(activity!!.applicationContext, list)
    }

}
