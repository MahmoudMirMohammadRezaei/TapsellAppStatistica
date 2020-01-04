package com.example.tapsell.helpers

import com.github.mfathi91.time.PersianDate
import com.ibm.icu.util.PersianCalendar

import java.time.Year
import java.time.ZoneId
import java.util.*
import java.util.Calendar.WEEK_OF_YEAR
import java.util.Calendar.YEAR

fun Date.getWeekNumberPersian():Int{
    val cal = PersianCalendar.getInstance()
    cal.time = this
    return  cal.get(WEEK_OF_YEAR)
}

fun Date.getYearNumberPersian():Int{
    val cal = PersianCalendar.getInstance()
    cal.time = this
    return  cal.get(YEAR)
}

fun String.ConvertToGregorian(): Date {
    val cal = this.split("-")
    val year = cal[0].toInt()
    val month = cal[1].toInt()
    val day = cal[2].toInt()

    val persianDate = PersianDate.of(year, month, day)
    val gregorianDate = persianDate.toGregorian()
    return Date.from(gregorianDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
}