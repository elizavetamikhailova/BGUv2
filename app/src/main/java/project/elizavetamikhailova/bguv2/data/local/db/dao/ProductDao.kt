package project.elizavetamikhailova.bguv2.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe
import project.elizavetamikhailova.bguv2.view.uimodels.Product

@Dao
interface ProductDao {

    @Insert
    fun insert(product: Product)

    @Query("SELECT * FROM product")
    fun getAll(): Maybe<List<Product>>

    @Query("SELECT * FROM product WHERE product.categoryName = :categoryName")
    fun getByCategoryName(categoryName : String): Maybe<List<Product>>

    @Delete
    fun delete(product: Product)

    @Query("DELETE FROM product")
    fun deleteAll()
}