package com.io.fizmat.worktotime

import android.util.Log
import java.util.*

object CurrentOccupation {

    var shirt = 1

    fun firstShirt():Int
    {
        val calendar = GregorianCalendar()
        val min: Int = calendar.get(Calendar.MINUTE)
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        Log.d("Data","$hour + $min")
        if (hour in 8..9) {
            if (min < 50 || hour < 9) return 1
        }
        if (hour in 9..11) {
            if (hour < 11 || min < 25) return 2
        }
        if (hour in 11..13) {
            if (hour < 13 || min < 15) return 3
        }
        if (hour in 13..14) {
            if (hour < 14 || min < 51) return 4
        }
        if (hour < 8)return  -1
        return 0
    }

    fun secondShirt():Int {
        val calendar = GregorianCalendar()
        val min: Int = calendar.get(Calendar.MINUTE)
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        if (hour in 13..14) {
            if (min < 51 || hour < 14) return 1
        }
        if (hour in 15..16) {
            if (hour < 16 || min < 21) return 2
        }
        if (hour in 16..17) {
            if (hour < 17 || min < 51) return 3
        }
        if (hour in 18..19) {
            if (hour < 20 || min < 21) return 4
        }
        if (hour < 13 ) {
                return -1
        }
        return 0
    }
}