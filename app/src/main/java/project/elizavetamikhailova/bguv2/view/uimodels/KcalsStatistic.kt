package project.elizavetamikhailova.bguv2.view.uimodels

import androidx.databinding.BaseObservable
import project.elizavetamikhailova.bguv2.helpers.enums.NutrientType

class KcalsStatistic(
    _summary : Int,
    val normOfKcals: Int) : BaseObservable() {
    val nutrientType = NutrientType.KCALS
    var progress : Int
    var isNormExceed : Boolean = false
    val summary = _summary
    init {
        progress = countProgress(normOfKcals)
        isNormExceed = summary < progress
    }


    private fun countProgress(normOfKclas : Int) : Int{ //подсчет прогресса
        return (100 / (normOfKclas.toDouble() / summary.toDouble())).toInt()
    }
}