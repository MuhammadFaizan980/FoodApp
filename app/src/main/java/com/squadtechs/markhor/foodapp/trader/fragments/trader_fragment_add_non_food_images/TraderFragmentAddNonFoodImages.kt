package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_images


import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.TraderMainCallBack

class TraderFragmentAddNonFoodImages : Fragment() {

    private lateinit var imgGoBack: ImageView
    private lateinit var imgFirstImage: ImageView
    private lateinit var imgSecondImage: ImageView
    private lateinit var imgThirdImage: ImageView
    private var uri1: Uri? = null
    private var uri2: Uri? = null
    private var uri3: Uri? = null
    private lateinit var mView: View
    private lateinit var obj: TraderMainCallBack

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_add_non_food_images, container, false)
        initViews()
        setListeners()
        return mView
    }

    private fun setListeners() {
        imgGoBack.setOnClickListener {
            obj.onFragmentTap("non_images")
        }

        imgFirstImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
        imgSecondImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
        }
        imgThirdImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 3)
        }
    }

    private fun initViews() {
        imgGoBack = mView.findViewById(R.id.img_go_back)
        imgFirstImage = mView.findViewById(R.id.img_product_first)
        imgSecondImage = mView.findViewById(R.id.img_product_second)
        imgThirdImage = mView.findViewById(R.id.img_product_third)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri1 = data!!.data
            imgFirstImage.setImageURI(uri1)
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            uri2 = data!!.data
            imgSecondImage.setImageURI(uri2)
        } else if (requestCode == 3 && resultCode == RESULT_OK) {
            uri3 = data!!.data
            imgThirdImage.setImageURI(uri3)
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        obj = activity!! as TraderMainCallBack
    }


}
