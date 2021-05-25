package com.ps.loginregistration

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window

class SessionSession {

    public var abc: String? = "";


    public fun getValues(): String? {
        var string: String? = ""
        string="..........................Me hu Na.........................."
        return string
    }

    var dialogProgress: Dialog? = null

    fun LoadingSpinner(mContext: Context?): Dialog? {
        dialogProgress = Dialog(mContext!!, android.R.style.Theme_Black)
        val _view: View =
            LayoutInflater.from(mContext).inflate(R.layout.custom_loader, null)
        dialogProgress!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogProgress!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialogProgress!!.setContentView(_view)
        return dialogProgress
    }

}