package com.io.fizmat.worktotime

import android.util.Log
import java.util.*

object DayOfWeek {

    var day:Int = 0

    fun dayofWeek()
    {
        day = GregorianCalendar().get(Calendar.DAY_OF_WEEK)
        if (day == 1) day = 8
        day-=2

        val occupationSelected = getOcupation()

        if (occupationSelected == 0) day++
        if (day == 6 || day == 7) {
            day = 0
            CurrentOccupation.shirt = 3
        }
        Log.d("DayOfWeekUpdate","update to object $day")
    }

    fun getOcupation(): Int = if (CurrentOccupation.shirt == 1) CurrentOccupation.firstShirt()
    else if (CurrentOccupation.shirt == 2)CurrentOccupation.secondShirt()
    else -1
}