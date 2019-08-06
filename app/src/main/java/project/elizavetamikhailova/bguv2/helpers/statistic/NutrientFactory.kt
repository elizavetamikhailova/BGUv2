package project.elizavetamikhailova.bguv2.helpers.statistic

import project.elizavetamikhailova.bguv2.helpers.enums.NutrientType

class NutrientFactory {
    companion object{
        fun createNutrientKkals(nutrientType: NutrientType) =
            when (nutrientType) {
                    NutrientType.PROTS -> 4
                    NutrientType.FATS ->  9
                    NutrientType.CARBS -> 4
                    NutrientType.KCALS -> 1 //TODO придумать что-нибудь
            }
        }
    }
