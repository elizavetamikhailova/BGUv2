package project.elizavetamikhailova.bguv2.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe
import project.elizavetamikhailova.bguv2.view.uimodels.Category

@Dao
interface CategoryDao {

    @Insert
    fun insert(category: Category)

    @Query("SELECT * FROM category")
    fun getAll(): Maybe<List<Category>>

    @Delete
    fun delete(category: Category)

    @Query("DELETE FROM category")
    fun deleteAll()
}