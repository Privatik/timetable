package com.io.fizmat.xlsreader.model;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.io.fizmat.singoltonbase.savebaseroom.typeconvert.ConvertGroup;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Group implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String nameGroup;

    @TypeConverters({ConvertGroup.class})
    private List<Day> dayList;

    @ColumnInfo(index = true)
    private String curcTitle;

    public Group(String nameGroup, String curcTitle)
    {
        this.curcTitle = curcTitle;
        this.nameGroup = nameGroup;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public void addTimeTableString(Integer dayNumber,String string)
    {
        Day day = dayList.get(dayNumber);
      //  System.out.println(nameGroup + " " + string);
        day.addTimetable(string);
    }

    public String getCurcTitle() {
        return curcTitle;
    }

    public void setCurcTitle(String curcTitle) {
        this.curcTitle = curcTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
