package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_images


import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.ActivityTraderMain
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.TraderMainCallBack
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class TraderFragmentAddNonFoodImages : Fragment() {

    private lateinit var imgGoBack: ImageView
    private lateinit var imgFirstImage: ImageView
    private lateinit var imgSecondImage: ImageView
    private lateinit var imgThirdImage: ImageView
    private lateinit var API: String
    private lateinit var btnUpload: Button
    private lateinit var pref: SharedPreferences
    private var uri1: Uri? = null
    private var uri2: Uri? = null
    private var uri3: Uri? = null
    private lateinit var mView: View
    private lateinit var obj: TraderMainCallBack
    private lateinit var map: HashMap<String, String>

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
            val intent =
                Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
        imgSecondImage.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
        }
        imgThirdImage.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 3)
        }
        pref =
            activity!!.getSharedPreferences("item_id", Context.MODE_PRIVATE)
    }

    private fun initViews() {
        imgGoBack = mView.findViewById(R.id.img_go_back)
        imgFirstImage = mView.findViewById(R.id.img_product_first)
        imgSecondImage = mView.findViewById(R.id.img_product_second)
        imgThirdImage = mView.findViewById(R.id.img_product_third)
        btnUpload = mView.findViewById(R.id.btn_upload)
        btnUpload.setOnClickListener {
            if (uri1 != null && uri2 != null && uri3 != null) {

                map = HashMap<String, String>()
                if (activity!!.getSharedPreferences(
                        "add_item_preferences",
                        Context.MODE_PRIVATE
                    ).getBoolean(
                        "is_edit",
                        false
                    )
                ) {
                    API = "http://squadtechsolution.com/android/v1/update_NonFoodImages.php"
                    map["id"] = pref.getString("value", "null") as String
                    map["nonFoodImage"] = getImageString(uri1)
                    map["nonFoodImage2"] = getImageString(uri2)
                    map["nonFoodImage3"] = getImageString(uri3)
                } else {
                    API = "http://squadtechsolution.com/android/v1/nonFoodImages.php"
                    map["nonFood_id"] = pref.getString("value", "null") as String
                    map["nonFoodImage"] = getImageString(uri1)
                    map["nonFoodImage2"] = getImageString(uri2)
                    map["nonFoodImage3"] = getImageString(uri3)
                }

                val pd = MainUtils.getLoadingDialog(activity!!, "Adding", "Please wait", false)
                pd.show()
                val requestQueue = Volley.newRequestQueue(activity!!)
                val stringRequest = object : StringRequest(
                    Method.POST,
                    API,
                    Response.Listener { response ->
                        Log.i("img_response", response)
                        pd.cancel()
                        try {
                            val json = JSONObject(response)
                            if (json.getString("status").equals("Images Uploaded")) {
                                Toast.makeText(
                                    activity!!,
                                    "Images added successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                startActivity(Intent(activity!!, ActivityTraderMain::class.java))
                                activity!!.finish()
                            }
                        } catch (exc: Exception) {
                            Toast.makeText(activity!!, exc.toString(), Toast.LENGTH_LONG).show()
                        }
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(activity!!, error.toString(), Toast.LENGTH_LONG).show()
                        pd.cancel()
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        return map
                    }
                }
                stringRequest.setRetryPolicy(
                    DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
                );
                requestQueue.add(stringRequest)

            } else {
                Toast.makeText(activity!!, "Select 3 images first", Toast.LENGTH_LONG).show()
            }
        }
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

    private fun getImageString(uri: Uri?): String {

        if (uri == null) {
            return "n/a"
        } else {
            val stream = ByteArrayOutputStream()
            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, uri)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
            val arr = stream.toByteArray()
            return Base64.encodeToString(arr, Base64.DEFAULT)
        }
    }

}
