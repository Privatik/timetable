package com.io.fizmat.app

import android.app.Application
import android.util.Log
import com.io.fizmat.assynctask.GetListCurs
import com.io.fizmat.singoltonbase.BaseCurses
import com.io.fizmat.xlsreader.XLSReaderBRSU
import com.io.fizmat.xlsreader.model.Curs
import java.net.URL

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        Log.d("ApplicationFlex","onCreate")
        BaseCurses.inization(this)

    }

}