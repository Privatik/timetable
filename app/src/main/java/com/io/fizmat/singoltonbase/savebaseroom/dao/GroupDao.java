package com.io.fizmat.singoltonbase.savebaseroom.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.io.fizmat.xlsreader.model.Group;

import java.util.List;

@Dao
public interface GroupDao {

    @Query("SELECT * FROM `group` WHERE  curcTitle = :cursTitle")
    List<Group> getListGroup(String cursTitle);

    @Query("SELECT * FROM `group`")
    List<Group> getListGroup();

    @Insert
    void insert(Group group);

    @Delete
    void delete(Group group);

    @Update
    void update(Group group);
}
