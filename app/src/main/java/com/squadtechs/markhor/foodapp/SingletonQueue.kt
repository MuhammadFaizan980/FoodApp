package com.squadtechs.markhor.foodapp

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class SingletonQueue {
    companion object {
        var requestQueue: RequestQueue? = null
        fun getRequestQueue(context: android.content.Context): RequestQueue {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context)
            }
            return requestQueue!!
        }
    }
}