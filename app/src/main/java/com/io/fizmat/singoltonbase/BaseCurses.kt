package com.io.fizmat.singoltonbase

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.room.Room
import com.io.fizmat.assynctask.GetListCurs
import com.io.fizmat.singoltonbase.savebaseroom.dao.CursDao
import com.io.fizmat.singoltonbase.savebaseroom.dao.GroupDao
import com.io.fizmat.singoltonbase.savebaseroom.database.TimetableBase
import com.io.fizmat.util.UtilToast
import kotlinx.coroutines.*
import com.io.fizmat.xlsreader.model.Curs
import kotlin.coroutines.suspendCoroutine

object BaseCurses {

    lateinit var listCurs : Deferred<List<Curs>>
    var isLoad = false

    fun inization(context: Context) = runBlocking {
        val baseSQL = SQLbase(context)
        if (isConnect(context)) {
            listCurs = GetListCurs().get()
            baseSQL.putBaseCurs()
        } else {
            listCurs = baseSQL.getAllListCurs()
        }
    }


    private fun isConnect(context: Context):Boolean
    {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
}