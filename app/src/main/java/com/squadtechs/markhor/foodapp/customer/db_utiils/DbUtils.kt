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

}