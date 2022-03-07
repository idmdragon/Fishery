package com.idmdragon.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object ConverterHelper {

    fun convertMillisToString(timeMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMillis
        val sdf = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        return sdf.format(calendar.time)
    }

}