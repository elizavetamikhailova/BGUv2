package project.elizavetamikhailova.bguv2.data.local.type_converters

import androidx.room.TypeConverter
import project.elizavetamikhailova.bguv2.view.uimodels.Day
import java.util.*

class DayConverter {
    @TypeConverter
    fun fromDay(day: Day): String {
        return ("${day.day},${day.month},${day.year}")
    }

    @TypeConverter
    fun toDay(data: String): Day {
        val dataList = Arrays.asList(*data.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()).toMutableList()
        val day = Day()
        day.day = Integer.parseInt(dataList[0])
        day.month = Integer.parseInt(dataList[1])
        day.year = Integer.parseInt(dataList[2])
        return day
    }
}