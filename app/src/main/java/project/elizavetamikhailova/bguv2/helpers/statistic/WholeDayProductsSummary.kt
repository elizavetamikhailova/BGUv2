package project.elizavetamikhailova.bguv2.helpers.statistic

import androidx.databinding.BaseObservable
import project.elizavetamikhailova.bguv2.view.uimodels.Day
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct

data class WholeDayProductsSummary constructor(val userProducts: List<UserProduct>) : BaseObservable(){

    var allKcals : Int = 0
    var allProts : Int = 0
    var allFats : Int = 0
    var allCarbs : Int = 0
    var day = Day()

    init {
        userProducts.map {
            it.product?.apply {
                allKcals += getVal(kcals!!)
                allProts += getVal(prots!!)
                allFats += getVal(fats!!)
                allCarbs += getVal(carbs!!)
            }
        }
        if(userProducts.isNotEmpty()){
            userProducts[0].day?.let {
                day = it
            }
        }

    }


    private fun getVal(value : String) : Int{
        return Math.round(value.toDouble()).toInt()
    }

    override fun toString(): String {
        return day.toString()
    }

}