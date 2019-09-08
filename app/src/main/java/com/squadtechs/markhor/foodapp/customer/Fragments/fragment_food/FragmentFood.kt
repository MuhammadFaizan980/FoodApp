package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_food


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

/**
 * A simple [Fragment] subclass.
 */
class FragmentFood : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

}
