package com.io.fizmat.xlsreader.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
public class Curs {

    @PrimaryKey
    @NonNull
    private String cursTitle;

    @Ignore
    private List<Group> groupList;

    public Curs(){}

    public String getCursTitle() {
        return cursTitle;
    }

    public void setCursTitle(String cursTitle) {
        this.cursTitle = cursTitle;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

}
