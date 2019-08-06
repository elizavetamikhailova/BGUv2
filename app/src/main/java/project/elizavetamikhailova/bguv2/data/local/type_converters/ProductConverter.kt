package project.elizavetamikhailova.bguv2.data.local.type_converters

import androidx.room.TypeConverter
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import java.util.*

class ProductConverter {
    @TypeConverter
    fun fromProduct(product: Product): String {
        return "${product.id},${product.categoryName},${product.title},${product.kcals},${product.prots},${product.fats},${product.carbs}"
    }

    @TypeConverter
    fun toProduct(data: String): Product {
        val dataList = Arrays.asList(*data.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()).toMutableList()
        return Product(id = dataList[0].toInt(), categoryName = dataList[1], title = dataList[2],
            kcals = dataList[3], prots = dataList[4], fats = dataList[5], carbs = dataList[6])
    }
}