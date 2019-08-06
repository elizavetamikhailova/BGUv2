package project.elizavetamikhailova.bguv2.view.uimodels

import java.util.*

class Day(){
    var day: Int
    var month: Int
    var year: Int
    init {
        val calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
    }

    override fun toString(): String {
        return "$day.$month.$year"
    }

}