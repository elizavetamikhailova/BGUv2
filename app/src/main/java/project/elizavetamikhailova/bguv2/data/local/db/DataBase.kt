package project.elizavetamikhailova.bguv2.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import project.elizavetamikhailova.bguv2.data.local.db.dao.CategoryDao
import project.elizavetamikhailova.bguv2.data.local.db.dao.ProductDao
import project.elizavetamikhailova.bguv2.data.local.db.dao.UserProductDao
import project.elizavetamikhailova.bguv2.data.local.type_converters.DayConverter
import project.elizavetamikhailova.bguv2.data.local.type_converters.ProductConverter
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct

@Database(entities = [Category::class, Product::class, UserProduct::class], version = 5, exportSchema = false)
@TypeConverters(ProductConverter::class, DayConverter::class)
abstract class DataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun userProductDao(): UserProductDao

    companion object {
       const val DATABASE_NAME = "products.db"
    }
}