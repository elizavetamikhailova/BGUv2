package project.elizavetamikhailova.bguv2.view.uimodels

import androidx.databinding.BaseObservable
import project.elizavetamikhailova.bguv2.helpers.enums.NutrientType
import project.elizavetamikhailova.bguv2.helpers.statistic.NutrientFactory

//TODO rename to BGUstatistic
class DayStatistic (
    val nutrientType: NutrientType,
    _nutrientSummary : Int,
    _normOfNutrient: Double,
    private val normOfKcals: Int) : BaseObservable() {

    var normOfNutrientInt : Int //для dataBinding
    var progress : Int
    var isNormExceed : Boolean = false
    val nutrientSummary = _nutrientSummary
    val normOfNutrient = _normOfNutrient
    init {
        normOfNutrientInt = countNorm()
        progress = countProgress(normOfNutrientInt)
        isNormExceed = countNorm() < progress
    }

    private fun countNorm(): Int { //подсчет нормы
        return (Math.round(normOfKcals.toDouble() * normOfNutrient /
                NutrientFactory.createNutrientKkals(
                    nutrientType
                )
        )).toInt()
    }

    private fun countProgress(normOfNutrient : Int) : Int{ //подсчет прогресса
        return (100 / (normOfNutrient.toDouble() / nutrientSummary.toDouble())).toInt()
    }
}