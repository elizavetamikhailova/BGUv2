package project.elizavetamikhailova.bguv2.view.uimodels

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import project.elizavetamikhailova.bguv2.data.local.type_converters.DayConverter
import project.elizavetamikhailova.bguv2.data.local.type_converters.ProductConverter

@Entity
data class UserProduct(
    @PrimaryKey(autoGenerate = true)
    var id: Int ? = null,
    @TypeConverters(DayConverter::class)
    var day: Day? = null,
    @TypeConverters(ProductConverter::class)
    var product: Product? = null,
    var weight: String
) : BaseObservable()