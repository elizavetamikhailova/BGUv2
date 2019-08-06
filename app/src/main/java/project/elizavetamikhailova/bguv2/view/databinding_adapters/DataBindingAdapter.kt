package project.elizavetamikhailova.bguv2.view.databinding_adapters

import android.graphics.Color
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import project.elizavetamikhailova.bguv2.R
import project.elizavetamikhailova.bguv2.helpers.enums.NutrientType
import project.elizavetamikhailova.bguv2.helpers.statistic.NutrientFactory
import project.elizavetamikhailova.bguv2.helpers.statistic.NutrientIconFactory


@BindingAdapter("pulsing")
fun setPulsing(target: View, isVisible: Boolean) {
    val animationRotateCenter = AnimationUtils.loadAnimation(
        target.context, R.anim.pulsing
    )
    if (isVisible) target.startAnimation(animationRotateCenter) else View.GONE

}

@BindingAdapter("progressColor")
fun setProgressColor (target: View, isNormExceed : Boolean){
    target.resources.apply {
        (target as ProgressBar).progressDrawable = if (isNormExceed) {
            getDrawable(R.drawable.red_horizontal_progress_bar)
        } else {
            getDrawable(R.drawable.green_horizontal_progress_bar)
        }
    }
}

@BindingAdapter("textColor")
fun setTextColor (target: View, isNormExceed : Boolean) {
    (target as TextView).setTextColor(
        if (isNormExceed) {
            Color.parseColor("#FFFFFF")
        } else {
            Color.parseColor("#FD5523")
        }
    )
}

@BindingAdapter("nutrientType", "isNormExceed")
fun setIconColor (target: View, nutrientType: NutrientType, isNormExceed : Boolean) {
    target.apply {
        background =
            resources.getDrawable(NutrientIconFactory.createNutrientIcon(nutrientType = nutrientType, isNormExceed = isNormExceed))
    }
}

@BindingAdapter("summary", "normOfKcals")
fun setIconforStatistic (target: View, summary: Int, normOfKcals: Int) {
    target.resources.apply {
        target.apply {
            background = if(summary - normOfKcals < -100){
                getDrawable(R.drawable.min)
            }else{
                if(summary - normOfKcals > 50){
                    getDrawable(R.drawable.big_attention)
                }else{
                    getDrawable(R.drawable.ok)
                }
            }
        }
    }
}
