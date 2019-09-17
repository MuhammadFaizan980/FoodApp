package com.squadtechs.markhor.foodapp.main_utils

import android.app.AlertDialog
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

        fun getMessageDialog(
            context: Context,
            title: String,
            message: String,
            flag: Boolean
        ): AlertDialog.Builder {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(title)
            dialog.setMessage(message)
            dialog.setCancelable(flag)
            return dialog
        }
    }
}