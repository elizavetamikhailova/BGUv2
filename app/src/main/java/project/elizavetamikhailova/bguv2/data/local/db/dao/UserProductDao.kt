package project.elizavetamikhailova.bguv2.data.local.db.dao

import androidx.room.*
import io.reactivex.Maybe
import project.elizavetamikhailova.bguv2.data.local.type_converters.DayConverter
import project.elizavetamikhailova.bguv2.view.uimodels.Day
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct

@Dao
interface UserProductDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userProducts: UserProduct): Long?

    @Query("SELECT * FROM UserProduct WHERE UserProduct.day = :day")
    fun getByDay(@TypeConverters(DayConverter::class) day: Day): Maybe<List<UserProduct>>

    @Query("SELECT * FROM UserProduct")
    fun getAll():List<UserProduct>

    @Query("SELECT * FROM UserProduct WHERE UserProduct.day = :day OR UserProduct.day = :day1 OR UserProduct.day = :day2 " +
            "OR UserProduct.day = :day3 OR UserProduct.day = :day4 OR UserProduct.day = :day5 OR UserProduct.day = :day6")
    fun getByWeek(
        @TypeConverters(DayConverter::class) day: Day,
        @TypeConverters(DayConverter::class) day1: Day,
        @TypeConverters(DayConverter::class) day2: Day,
        @TypeConverters(DayConverter::class) day3: Day,
        @TypeConverters(DayConverter::class) day4: Day,
        @TypeConverters(DayConverter::class) day5: Day,
        @TypeConverters(DayConverter::class) day6: Day
    ): Maybe<List<UserProduct>>

    @Query("DELETE FROM UserProduct WHERE UserProduct.id = :id")
    fun deleteById(id: Int)

    @Query("DELETE FROM UserProduct")
    fun deleteAll()


}