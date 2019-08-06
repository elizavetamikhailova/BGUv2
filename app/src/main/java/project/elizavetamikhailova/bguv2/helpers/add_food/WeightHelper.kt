package project.elizavetamikhailova.bguv2.helpers.add_food

import android.util.Log
import androidx.databinding.ObservableField
import project.elizavetamikhailova.bguv2.view.uimodels.Product

class WeightHelper {


    private fun getVal(value : String, weight : Int) : String{
        Log.i("KKALS", value)
        return Math.floor((value.toDouble() / 100) * weight).toInt().toString()
    }

    fun updateProductValueByWeight(product : ObservableField<Product>, weight : ObservableField<Int>):Product{

        product.get()!!.apply {
            return Product(id = id, categoryName = categoryName,
                title = title, kcals = getVal(kcals!!, weight.get()!!),
                prots = getVal(prots!!, weight.get()!!), fats = getVal(fats!!, weight.get()!!), carbs = getVal(carbs!!, weight.get()!!))
        }
    }
}