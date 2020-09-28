package com.io.fizmat.singoltonbase

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.io.fizmat.singoltonbase.savebaseroom.dao.CursDao
import com.io.fizmat.singoltonbase.savebaseroom.dao.GroupDao
import com.io.fizmat.singoltonbase.savebaseroom.database.TimetableBase
import com.io.fizmat.xlsreader.model.Curs
import kotlinx.coroutines.*

class SQLbase(val context: Context) {
    private lateinit var cursDao: CursDao
    private lateinit var groupDao: GroupDao

    init {
     baseCreate()
    }

    private fun baseCreate()
    {
        val base = Room.databaseBuilder(context, TimetableBase::class.java,"basee").build()
        cursDao = base.cursDao()
        groupDao = base.groupDao()
    }

    fun putBaseCurs() = runBlocking{
        launch(Dispatchers.IO){
            val firstStart = FirstStart(context)
            if (firstStart.getFirstStart()) {
                insertBase()
                firstStart.setFirstStart()
            } else {
                updateBase()
            }
        }
    }

    private suspend fun insertBase()
    {
        BaseCurses.listCurs.await().forEach{
            Log.d("BaseReader",it.cursTitle)
            cursDao.insert(it)
            it.groupList.forEach { group ->
                Log.d("BaseReader"," ${group.nameGroup}")
                groupDao.insert(group)
            }
        }
    }

    private suspend fun updateBase()
    {
        val listCurs = cursDao.listCurs as ArrayList<Curs>

        listCurs.forEach {
            it.groupList = groupDao.getListGroup(it.cursTitle)

            it.groupList.forEach {group ->
                groupDao.delete(group)
            }

            cursDao.delete(it)
        }

        insertBase()
    }


    fun getAllListCurs(): Deferred<List<Curs>> = GlobalScope.async {
        try {
            val listCurs = cursDao.listCurs as ArrayList<Curs>

            listCurs.forEach {
                Log.d("BaseReaderList", it.cursTitle)
                it.groupList = groupDao.getListGroup(it.cursTitle)
                it.groupList.forEach { group ->
                    Log.d("BaseReaderList", "${group.nameGroup} ${group.curcTitle}")
                }
            }
            return@async listCurs
        }catch (e: Exception)
        {
            val list: List<Curs> = arrayListOf()
            return@async list
        }
    }
}