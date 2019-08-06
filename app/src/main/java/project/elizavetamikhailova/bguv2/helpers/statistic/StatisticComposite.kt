package project.elizavetamikhailova.bguv2.helpers.statistic

import androidx.databinding.BaseObservable
import project.elizavetamikhailova.bguv2.helpers.enums.NutrientType
import project.elizavetamikhailova.bguv2.view.uimodels.DayStatistic
import project.elizavetamikhailova.bguv2.view.uimodels.KcalsStatistic
import project.elizavetamikhailova.bguv2.view.uimodels.User
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct

class StatisticComposite(private val wholeDayProductsSummary: WholeDayProductsSummary, val user: User) : BaseObservable(){
    var protsStatistic: DayStatistic
    var fatsStatistic : DayStatistic
    var carbsStatistic : DayStatistic
    var kcalsStatistic : KcalsStatistic

    init {
        user.apply {
            wholeDayProductsSummary.apply {
                protsStatistic = DayStatistic(nutrientType = NutrientType.PROTS, _nutrientSummary = allProts,
                    _normOfNutrient = normOfProts, normOfKcals = normOfKcals)
                fatsStatistic = DayStatistic(nutrientType = NutrientType.FATS, _nutrientSummary = allFats,
                    _normOfNutrient = normOfFats, normOfKcals = normOfKcals)
                carbsStatistic = DayStatistic(nutrientType = NutrientType.CARBS, _nutrientSummary = allCarbs,
                    _normOfNutrient = normOfCarbs, normOfKcals = normOfKcals)
                kcalsStatistic = KcalsStatistic(_summary = allKcals, normOfKcals = normOfKcals)
            }
        }
    }

}