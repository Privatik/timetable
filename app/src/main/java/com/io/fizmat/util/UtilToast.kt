package com.io.fizmat.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object UtilToast {

    lateinit var activity: AppCompatActivity

    fun show(message: String)
    {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }

}