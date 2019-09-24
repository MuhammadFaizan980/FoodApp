package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.db_utiils.DbUtils

class CustomerFragmentCart : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<String>
    private lateinit var adapter: CartMainAdapter
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.customer_fragment_cart, container, false)
        initViews()
        fetchData()
        return mView
    }

    private fun fetchData() {
        list = DbUtils(activity!!).getCompanies()
        adapter = CartMainAdapter(list, activity!!)
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        recyclerView.adapter = adapter
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

}
