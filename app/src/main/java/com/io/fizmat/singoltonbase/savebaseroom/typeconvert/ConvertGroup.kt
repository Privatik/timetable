package com.io.fizmat.singoltonbase.savebaseroom.typeconvert

import androidx.room.TypeConverter
import com.io.fizmat.xlsreader.model.Day
import java.lang.StringBuilder
import java.util.stream.Collectors

class ConvertGroup {

    @TypeConverter
    fun fromString(listDay: List<Day>):String
    {
        val stringBuilder = StringBuilder("")
        listDay.forEachIndexed { index, day ->
            stringBuilder.append(day.timetable.joinToString(":::"))
            if (index != listDay.size - 1)
                stringBuilder.append("---")
        }
        return stringBuilder.toString()
    }

    @TypeConverter
    fun toString(string: String):List<Day>
    {
        val list = arrayListOf<Day>()

        val dayArr = string.split("---")

        dayArr.forEach {
            val day = Day()
            day.timetable = it.split(":::")
            list.add(day)
        }

        return list
    }
}