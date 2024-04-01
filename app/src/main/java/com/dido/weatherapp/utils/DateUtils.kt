package com.dido.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    const val TIME = "HH:mm"
    const val TIME_AM_PM = "HH aa"
    const val DATE_DAY_SHORT = "EEE"
    const val DATE_FULL = "yyyy-MM-dd HH:mm:ss"

    /**
     * Get current time in the specified format.
     * @param format Custom date format.
     *
     */
    fun getDateMillis(date: String, format: String = TIME): Long {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.parse(date)?.time ?: 0
    }

    /**
     * Get formatted date from timestamp.
     * @param date Date in String.
     * @param format Custom date format.
     */
    fun formatDate(dateString: String, format: String = DATE_FULL, resultFormat: String = DATE_DAY_SHORT): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        val date = sdf.parse(dateString)
        val sdfResult = SimpleDateFormat(resultFormat, Locale.getDefault())
        return sdfResult.format(date as Date)
    }

}