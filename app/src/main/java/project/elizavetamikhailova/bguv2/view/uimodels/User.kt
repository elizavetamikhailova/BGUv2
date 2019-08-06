package project.elizavetamikhailova.bguv2.view.uimodels

import androidx.databinding.BaseObservable

data class User(
    var normOfKcals : Int = 1490,
    var normOfProts : Double = 0.4,
    var normOfFats : Double = 0.35,
    var normOfCarbs : Double = 0.25
) : BaseObservable()