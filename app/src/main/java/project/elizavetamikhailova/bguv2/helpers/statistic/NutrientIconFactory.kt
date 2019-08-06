package project.elizavetamikhailova.bguv2.helpers.statistic

import project.elizavetamikhailova.bguv2.R
import project.elizavetamikhailova.bguv2.helpers.enums.NutrientType

class NutrientIconFactory {
    companion object{
        fun createNutrientIcon(nutrientType: NutrientType, isNormExceed : Boolean) =
            if(isNormExceed){
                when (nutrientType) {
                    NutrientType.PROTS -> R.drawable.white_prots
                    NutrientType.FATS ->  R.drawable.white_fats
                    NutrientType.CARBS -> R.drawable.white_carbs
                    NutrientType.KCALS -> R.drawable.white_kkals
                }
            }else{
                when (nutrientType) {
                    NutrientType.PROTS -> R.drawable.prots
                    NutrientType.FATS ->  R.drawable.fats
                    NutrientType.CARBS -> R.drawable.carbs
                    NutrientType.KCALS -> R.drawable.kcal
                }
            }

    }
}