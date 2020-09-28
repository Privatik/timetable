package com.io.fizmat.view.fragment

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.io.fizmat.adapter.AdapterCurs
import com.io.fizmat.singoltonbase.BaseCurses
import com.io.fizmat.util.UtilToast
import com.io.fizmat.xlsreader.model.Curs
import kotlinx.coroutines.*

class FragmentCurs : FragmentMain() {

    override fun workRecycltView(recyclerView: RecyclerView?){
        if (!BaseCurses.isLoad) {
            UtilToast.show("Загрузка")
            BaseCurses.isLoad = true
        }
            GlobalScope.launch {
                val list = BaseCurses.listCurs.await()
                withContext(Dispatchers.Main) {
                    if (list.isEmpty()) {
                        UtilToast.show("Ошибка загрузки")
                    } else {
                        super.workRecycltView(recyclerView)
                        recyclerView?.adapter = AdapterCurs(list)
                    }
                }
            }
    }

}