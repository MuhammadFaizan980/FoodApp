package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_dish


import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.ActivityTraderMain
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class TraderFragmentAddDish : Fragment() {

    private lateinit var spinnerListDishAs: Spinner
    private lateinit var mView: View
    private lateinit var linearDeliveryPrice: LinearLayout
    private lateinit var edtTitle: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtPrice: EditText
    private lateinit var txtTitle: TextView
    private lateinit var edtDeliveryPrice: EditText
    private lateinit var imgDish: ImageView
    private lateinit var btnAddDish: Button
    private var uri: Uri? = null
    private lateinit var doYouDeliver: String
    private var dishContains: String = ""
    private var meat: String = ""
    private var gluten: String = ""
    private var seafood: String = ""
    private var vegan: String = ""
    private var nuts: String = ""
    private var vegetarian: String = ""
    private var dairy: String = ""
    private var pork: String = ""
    private var listDishAs: String = "Main"
    private lateinit var map: HashMap<String, String>
    private lateinit var API: String
    private lateinit var imageAPI: String
    private lateinit var food_id: String

    //CHECK_BOXES
    private lateinit var checkMeat: CheckBox
    private lateinit var checkGlutenFree: CheckBox
    private lateinit var checkSeaFood: CheckBox
    private lateinit var checkVegan: CheckBox
    private lateinit var checkNuts: CheckBox
    private lateinit var checkVegetarian: CheckBox
    private lateinit var checkDairy: CheckBox
    private lateinit var checkPork: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_add_dish, container, false)
        initViews()
        populateSpinner()
        setcheckListener()
        setListener()
        return mView
    }

    private fun setcheckListener() {
        checkMeat.setOnClickListener {
            if (checkMeat.isChecked) {
                meat = " Meat,"
            } else {
                meat = ""
            }
        }
        checkGlutenFree.setOnClickListener {
            if (checkGlutenFree.isChecked) {
                gluten = " Gluten-free,"
            } else {
                gluten = ""
            }
        }
        checkSeaFood.setOnClickListener {
            if (checkSeaFood.isChecked) {
                seafood = " Seafood,"
            } else {
                seafood = ""
            }
        }
        checkVegan.setOnClickListener {
            if (checkVegan.isChecked) {
                vegan = " Vegan,"
            } else {
                vegan = ""
            }
        }
        checkNuts.setOnClickListener {
            if (checkNuts.isChecked) {
                nuts = " Nuts,"
            } else {
                nuts = ""
            }
        }
        checkVegetarian.setOnClickListener {
            if (checkVegetarian.isChecked) {
                vegetarian = " Vegetarian,"
            } else {
                vegetarian = ""
            }
        }
        checkDairy.setOnClickListener {
            if (checkDairy.isChecked) {
                dairy = " Dairy,"
            } else {
                dairy = ""
            }
        }
        checkPork.setOnClickListener {
            if (checkPork.isChecked) {
                pork = " Pork,"
            } else {
                pork = ""
            }
        }
    }

    private fun setListener() {
        imgDish.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), 78)
        }

        btnAddDish.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val description = edtDescription.text.toString().trim()
            val price = edtPrice.text.toString().trim()
            val deliveryPrice = edtDeliveryPrice.text.toString().trim()
            dishContains = meat + gluten + dairy + pork + seafood + nuts + vegan + vegetarian

            if (title.equals("") || description.equals("") || price.equals("")
                || dishContains.equals("") || uri == null
            ) {
                Toast.makeText(
                    activity!!,
                    "Fill all fields first and select a proper image",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                map = HashMap<String, String>()
                if (activity!!.getSharedPreferences(
                        "add_item_preferences",
                        Context.MODE_PRIVATE
                    ).getBoolean(
                        "is_edit",
                        false
                    )
                ) {
                    API = "http://squadtechsolution.com/android/v1/updateFood.php"
                    imageAPI = "http://squadtechsolution.com/android/v1/update_FoodImages.php"

                    map["food_id"] = activity!!.getSharedPreferences(
                        "add_item_preferences",
                        Context.MODE_PRIVATE
                    ).getString("food_id", "null") as String
                    map["dash_name"] = title
                    map["dash_description"] = description
                    map["price"] = price
                    map["list_dish_as"] = listDishAs
                    map["dish_contain"] = dishContains
                    map["food_deliveryPrice"] = deliveryPrice
                    map["trader_id"] = activity!!.getSharedPreferences(
                        "user_credentials",
                        Context.MODE_PRIVATE
                    ).getString("id", "na") as String
                } else {
                    API = "http://squadtechsolution.com/android/v1/food.php"
                    imageAPI = "http://squadtechsolution.com/android/v1/foodImages.php"
                    map["dash_name"] = title
                    map["dash_description"] = description
                    map["price"] = price
                    map["list_dish_as"] = listDishAs
                    map["dash_contain"] = dishContains
                    map["food_deliveryPrice"] = deliveryPrice
                    map["trader_id"] = activity!!.getSharedPreferences(
                        "user_credentials",
                        Context.MODE_PRIVATE
                    ).getString("id", "na") as String
                }

                val pd = MainUtils.getLoadingDialog(activity!!, "Adding", "Please wait", false)
                pd.show()

                val requestQueue = Volley.newRequestQueue(activity!!)
                val stringRequest = object : StringRequest(
                    Method.POST,
                    API,
                    Response.Listener { response ->
                        pd.cancel()
                        Log.i("m_response", response)
                        try {
                            val json = JSONObject(response)
                            if (json.getString("status").equals("Food Uploaded ") || json.getString(
                                    "status"
                                ).equals("Food Updated ")
                            ) {
                                food_id = json.getInt("food_id").toString()
                                uploadImage(food_id)
                            } else {
                                Toast.makeText(activity!!, "There was an error", Toast.LENGTH_LONG)
                                    .show()
                            }
                        } catch (exc: Exception) {
                            Log.i("exception_update_dish", exc.toString())
                            food_id = activity!!.getSharedPreferences(
                                "add_item_preferences",
                                Context.MODE_PRIVATE
                            ).getString("food_id", "null") as String
                            uploadImage(food_id)
                        }
                    },
                    Response.ErrorListener { error ->
                        pd.cancel()
                        Toast.makeText(activity!!, error.toString(), Toast.LENGTH_LONG).show()
                    }) {

                    override fun getParams(): MutableMap<String, String> {
                        Log.i("dxdiag", "\n\n\n${map["dash_contain"]}\n\n\n")
                        return map
                    }
                }
                requestQueue.add(stringRequest)

            }

        }

    }

    private fun uploadImage(food_id: String) {
        val pd = MainUtils.getLoadingDialog(activity!!, "Adding Image", "Please wait", false)
        pd.show()
        val stream = ByteArrayOutputStream()
        val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, uri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        val arr = stream.toByteArray()
        val imgString = Base64.encodeToString(arr, Base64.DEFAULT)

        val mMap = HashMap<String, String>()
        if (activity!!.getSharedPreferences(
                "add_item_preferences",
                Context.MODE_PRIVATE
            ).getBoolean(
                "is_edit",
                false
            )
        ) {
            mMap["id"] = food_id
            mMap["foodImage"] = imgString
        } else {
            mMap["food_id"] = food_id
            mMap["foodImage"] = imgString
        }

        val requestQueue = Volley.newRequestQueue(activity!!)
        val stringRequest = object : StringRequest(
            Method.POST,
            imageAPI,
            Response.Listener { response ->
                Log.i("image_response", response)
                pd.cancel()
                try {
                    val json = JSONObject(response)
                    if (json.getString("status").equals("Images Uploaded") || json.getString("status").equals(
                            "Images Updated"
                        )
                    ) {
                        Toast.makeText(activity!!, "Product added successfully", Toast.LENGTH_LONG)
                            .show()
                        startActivity(Intent(activity!!, ActivityTraderMain::class.java))
                        activity!!.finish()
                    } else {
                        Toast.makeText(
                            activity!!,
                            "There was an error uploading the image",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (exc: Exception) {
                    Toast.makeText(activity!!, exc.toString(), Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Toast.makeText(activity!!, error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String> = mMap
        }
        stringRequest.setRetryPolicy(
            DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        )
        requestQueue.add(stringRequest)

    }

    private fun populateSpinner() {
        val arr = arrayOfNulls<String>(4)
        arr[0] = "Main"
        arr[1] = "Side"
        arr[2] = "Desert"
        arr[3] = "Starter"
        val arrayAdapter = ArrayAdapter<String>(
            activity!!,
            R.layout.list_dish_as_spinner_row,
            R.id.txt_list_dish_as_row_design,
            arr
        )
        spinnerListDishAs.adapter = arrayAdapter
        spinnerListDishAs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listDishAs = arr[p2] as String
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        txtTitle = mView.findViewById(R.id.txt_title)
        spinnerListDishAs = mView.findViewById(R.id.spinner_list_dish_as)
        linearDeliveryPrice = mView.findViewById(R.id.linear_delivery_price)
        edtTitle = mView.findViewById(R.id.edt_dish_name)
        edtDescription = mView.findViewById(R.id.edt_dish_description)
        edtPrice = mView.findViewById(R.id.edt_dish_price)
        edtDeliveryPrice = mView.findViewById(R.id.edt_dish_delivery_price)
        imgDish = mView.findViewById(R.id.img_dish_photo)
        btnAddDish = mView.findViewById(R.id.btn_next)
        doYouDeliver = activity!!.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        ).getString("delivery_type", "none") as String
        if (doYouDeliver.equals("yes")) {
            linearDeliveryPrice.visibility = View.VISIBLE
        }
        checkDairy = mView.findViewById(R.id.check_dairy)
        checkMeat = mView.findViewById(R.id.check_meat)
        checkGlutenFree = mView.findViewById(R.id.check_gluten_free)
        checkNuts = mView.findViewById(R.id.check_nuts)
        checkPork = mView.findViewById(R.id.check_pork)
        checkSeaFood = mView.findViewById(R.id.check_sea_food)
        checkVegan = mView.findViewById(R.id.check_vegan)
        checkVegetarian = mView.findViewById(R.id.check_vegetarian)

        if (activity!!.getSharedPreferences(
                "add_item_preferences",
                Context.MODE_PRIVATE
            ).getBoolean(
                "is_edit",
                false
            )
        ) {
            txtTitle.text = "Edit Dish"
            btnAddDish.text = "Edit Dish"
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 78 && resultCode == RESULT_OK) {
            uri = data!!.data!!
            imgDish.setImageURI(uri)
        }
    }

}
