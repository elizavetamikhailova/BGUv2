package project.elizavetamikhailova.bguv2.helpers.statistic

import android.util.Log
import project.elizavetamikhailova.bguv2.view.uimodels.Day
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct
import java.util.*

class DateSorter {

    fun setWeek(_day : Day) : List<Day> {
        val week = ArrayList<Day>()
        var dayOfWeek = _day.day
        var month = _day.month
        var year = _day.year

        if ((dayOfWeek == 1) and (month == 0)) {
            year -= 1
        }

        week.add(_day)
        for (i in 0..5) { //проработать первое января и високосный год
            //Log.i("KKALS", "$dayOfWeek $month")
            val day = Day()
            day.year = (year)
            if (dayOfWeek == 1) {
                month -= 1
                if (month == 0 || month == 2 || month == 4
                    || month == 6 || month == 7 || month == 9 || month == 11 || month == -1
                ) {
                    dayOfWeek = 31
                } else if (month == 1) {
                    dayOfWeek = 28
                } else {
                    dayOfWeek = 30
                }
            } else {
                dayOfWeek -= 1
            }
            day.day = (dayOfWeek)
            if (month == -1) {
                day.month = 11
            } else {
                day.month = month
            }
            if ((dayOfWeek == 1) and (month == 0)) {
                year -= 1
            }
            week.add(day)
        }
        //исп срок 2 мес 50 после 70 из плюсов большие извесные проекты

        Log.i("KKALS week", week.toString())
        return week
    }

    fun sortWeek(bguList: MutableList<WholeDayProductsSummary>): MutableList<WholeDayProductsSummary>{
        val bgus = arrayOfNulls<WholeDayProductsSummary>(bguList.size)
        for (i in bguList.indices) {
            bgus[i] = bguList.get(i)
            //Log.i("KKALS", "first " + bgus[i].getDay() + "." + bgus[i].getMonth())
        }

        for (i in 0 until bguList.size - 1) {
            if (bgus[i]!!.day.month > bgus[i + 1]!!.day.month) {
                //new
                var bgu = bgus[i]!!.copy()
                //var bgu = WholeDayProductsSummary(bgus[i]!!.userProducts)
//                var bgu1 = WholeDayProductsSummary(bgus[i + 1]!!.userProducts)
                var bgu1 = bgus[i + 1]!!.copy()
                bgus[i] = bgu1
                bgus[i + 1] = bgu
                for (j in 0 until i) {
                    if (bgus[j]!!.day.month > bgus[i]!!.day.month) {
                        //new
//                        bgu = WholeDayProductsSummary(bgus[j]!!.userProducts)
                        bgu = bgus[j]!!.copy()
//                        bgu1 = WholeDayProductsSummary(bgus[i]!!.userProducts)
                        bgu1 = bgus[i]!!.copy()
                        bgus[j] = bgu1
                        bgus[i] = bgu
                    } else if (bgus[i]!!.day.month == bgus[j]!!.day.month) {
                        if (bgus[j]!!.day.day > bgus[i]!!.day.day) {
                            //new
//                            bgu = WholeDayProductsSummary(bgus[j]!!.userProducts)
                            bgu = bgus[j]!!.copy()
                            bgu1 = bgus[i]!!.copy()
                            bgus[j] = bgu1
                            bgus[i] = bgu
                        }
                    }
                }
            } else if (bgus[i]!!.day.month == bgus[i + 1]!!.day.month) {
                if (bgus[i]!!.day.day > bgus[i + 1]!!.day.day) {
                    //new
//                    var bgu = WholeDayProductsSummary(bgus[i]!!.userProducts)
                    var bgu = bgus[i]!!.copy()
//                    var bgu1 = WholeDayProductsSummary(bgus[i + 1]!!.userProducts)
                    var bgu1 = bgus[i + 1]!!.copy()
                    bgus[i] = bgu1
                    bgus[i + 1] = bgu
                    for (j in 0 until i) {
                        if (bgus[i]!!.day.month == bgus[j]!!.day.month) {
                            if (bgus[j]!!.day.day > bgus[i]!!.day.day) {
                                //new
//                                bgu = WholeDayProductsSummary(bgus[j]!!.userProducts)
                                bgu = bgus[j]!!.copy()
                                bgu1 = bgus[i]!!.copy()
//                                bgu1 = WholeDayProductsSummary(bgus[i]!!.userProducts)
                                bgus[j] = bgu1
                                bgus[i] = bgu
                            }
                        }
                    }
                }
            }
        }
        val newBguList = ArrayList<WholeDayProductsSummary>()
        for (i in bgus.indices) {
            newBguList.add(bgus[i]!!)
        }

        return reverse(newBguList)
    }

    private fun reverse(bguList: List<WholeDayProductsSummary>): MutableList<WholeDayProductsSummary> {
        val reverseBguList = ArrayList<WholeDayProductsSummary>(bguList.size)
        for (i in bguList.indices.reversed()) {
            //new
            val bgu = bguList[i].copy()
            reverseBguList.add(bgu)
        }
        return reverseBguList
    }
}