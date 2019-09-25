package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.CustomerFoodFragmetnCallback
import com.squadtechs.markhor.foodapp.customer.db_utiils.DbUtils

class CustomerFragmentCart : Fragment(), CartCallBack {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<String>
    private lateinit var adapter: CartMainAdapter
    private lateinit var mView: View
    private lateinit var obj: CustomerFoodFragmetnCallback

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
        adapter = CartMainAdapter(list, activity!!, this@CustomerFragmentCart)
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        recyclerView.adapter = adapter
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

    override fun itemDeleted() {
        obj.onCartItemDeleted()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        obj = context as CustomerFoodFragmetnCallback
    }

}
