package com.io.fizmat.singoltonbase

import android.content.Context

class FirstStart(val context: Context) {

    val textBase = "firstStart"
    val sharedPreferences = context.getSharedPreferences(textBase, Context.MODE_PRIVATE)

    fun setFirstStart()
    {
        val edit = sharedPreferences.edit()
        edit.putBoolean(textBase, false)
        edit.apply()
    }

    fun getFirstStart():Boolean = sharedPreferences.getBoolean(textBase,true)
}