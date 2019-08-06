package project.elizavetamikhailova.bguv2.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import project.elizavetamikhailova.bguv2.data.local.db.DataBase
import project.elizavetamikhailova.bguv2.data.local.db.dao.CategoryDao
import project.elizavetamikhailova.bguv2.data.local.db.dao.ProductDao
import project.elizavetamikhailova.bguv2.data.local.db.dao.UserProductDao
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBase(context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            DataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(dataBase: DataBase): CategoryDao {
        return dataBase.categoryDao()
    }

    @Singleton
    @Provides
    fun provideProductDao(dataBase: DataBase): ProductDao {
        return dataBase.productDao()
    }

    @Singleton
    @Provides
    fun provideUserProductDao(dataBase: DataBase): UserProductDao{
        return dataBase.userProductDao()
    }
}