package com.io.fizmat.assynctask

import android.os.AsyncTask
import com.io.fizmat.singoltonbase.BaseCurses
import com.io.fizmat.xlsreader.XLSReaderBRSU
import com.io.fizmat.xlsreader.model.Curs
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL


class GetListCurs{

    fun get(): Deferred<List<Curs>> = GlobalScope.async{
        try {
                val url =
                    URL("https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1-j9vzxQP6WOr4eQS74Iu60aHnqb5yyaF")
                var inputStream = url.openStream()
                val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"), 8)

                val str = reader.readLine()

                reader.close()
                inputStream = URL(str).openStream()
                val xlsReaderBRSU = XLSReaderBRSU.newInstance(inputStream)

                inputStream.close()
            return@async xlsReaderBRSU.readXLS()
            } catch (e: KotlinNullPointerException) {
            val list: List<Curs> = arrayListOf()
            return@async list
            }
    }

}