package com.squadtechs.markhor.foodapp.customer.db_utiils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DbUtils(private val context: Context) : SQLiteOpenHelper(context, "customer_cart", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE customer_cart (id INTEGER PRIMARY KEY AUTOINCREMENT, item_title TEXT, item_id TEXT, item_price TEXT, delivery_price TEXT, is_food TEXT, quantity TEXT,company_id TEXT, customer_id TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        onCreate(p0!!)
    }

    fun insertData(obj: CartUtil) {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("item_title", obj.cart_item_title)
        contentValues.put("item_id", obj.item_id)
        contentValues.put("item_price", obj.cart_item_price)
        contentValues.put("delivery_price", obj.cart_item_delivery_price)
        contentValues.put("is_food", obj.is_food)
        contentValues.put("quantity", obj.cart_item_quantity)
        contentValues.put("company_id", obj.company_id)
        contentValues.put("customer_id", obj.customer_id)
        db.insert("customer_cart", null, contentValues)
        Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show()
    }

    fun getData(companyID: String, customerID: String): ArrayList<CartUtil> {
        val db: SQLiteDatabase = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM customer_cart WHERE customer_id = $customerID AND company_id = $companyID",
            null
        )
        val list: ArrayList<CartUtil> = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            var count = cursor.count
            while (count > 0) {
                val obj = CartUtil()
                obj.cart_item_title = cursor.getString(1)
                obj.cart_item_quantity = cursor.getString(6)
                obj.cart_item_price = cursor.getString(3)
                obj.item_id = cursor.getString(2)
                obj.cart_item_delivery_price = cursor.getString(4)
                obj.company_id = cursor.getString(7)
                obj.is_food = cursor.getString(5)
                obj.customer_id = cursor.getString(8)
                list.add(obj)
                count--
            }
        } else {
            Toast.makeText(context, "No item found", Toast.LENGTH_LONG).show()
        }
        return list
    }

    fun getCompanies(): ArrayList<String> {
        val db: SQLiteDatabase = this.readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT company_id FROM customer_cart", null)
        val list: ArrayList<String> = ArrayList()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            var count = cursor.count
            while (count > 0) {
                val companyID = cursor.getString(0)
                list.add(companyID)
                count--
            }
        } else {
            Toast.makeText(context, "No item found", Toast.LENGTH_LONG).show()
        }
        return list
    }

}