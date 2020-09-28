package com.io.fizmat.singoltonbase.savebaseroom.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.io.fizmat.xlsreader.model.Curs;

import java.util.List;

@Dao
public interface CursDao {

    @Query("SELECT * FROM curs")
    List<Curs> getListCurs();

    @Insert
    void insert(Curs curs);

    @Delete
    void delete(Curs curs);

    @Update
    void update(Curs curs);

}
