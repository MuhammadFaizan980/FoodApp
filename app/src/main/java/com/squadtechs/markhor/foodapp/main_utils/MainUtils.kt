package com.squadtechs.markhor.foodapp.main_utils

import android.app.ProgressDialog
import android.content.Context

class MainUtils {
    companion object {
        fun getLoadingDialog(
            context: Context,
            title: String,
            message: String,
            flag: Boolean
        ): ProgressDialog {
            val pd = ProgressDialog(context)
            pd.setTitle(title)
            pd.setMessage(message)
            pd.setCancelable(flag)
            return pd
        }
    }
}