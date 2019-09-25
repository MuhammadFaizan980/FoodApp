package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_image


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.CONSTANTS
import com.squadtechs.markhor.foodapp.R
import com.squareup.picasso.Picasso

class FragmentImage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_image, container, false)
        val imgView: ImageView = mView.findViewById(R.id.img_item)
        Picasso.get()
            .load("${CONSTANTS.imgPre}${arguments!!.getString("url")}")
            .into(imgView)
        return mView
    }


}
