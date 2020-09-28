package com.io.fizmat.singoltonbase.savebaseroom.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.io.fizmat.singoltonbase.savebaseroom.dao.CursDao;
import com.io.fizmat.singoltonbase.savebaseroom.dao.GroupDao;
import com.io.fizmat.xlsreader.model.Curs;
import com.io.fizmat.xlsreader.model.Group;

@Database(entities = {Curs.class, Group.class},version = 1)
public abstract class TimetableBase extends RoomDatabase {
   public abstract CursDao cursDao();
   public abstract GroupDao groupDao();
}
