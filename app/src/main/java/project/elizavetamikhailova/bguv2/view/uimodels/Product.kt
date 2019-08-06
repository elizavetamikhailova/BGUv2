package project.elizavetamikhailova.bguv2.view.uimodels

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    var id: Int,
    var categoryName : String,
    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("kkals")
    @Expose
    var kcals: String? = null,

    @SerializedName("prots")
    @Expose
    var prots: String? = null,
    @SerializedName("fats")
    @Expose
    var fats: String? = null,
    @SerializedName("carbs")
    @Expose
    var carbs: String? = null) : Parcelable, BaseObservable() {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(categoryName)
        parcel.writeString(title)
        parcel.writeString(kcals)
        parcel.writeString(prots)
        parcel.writeString(fats)
        parcel.writeString(carbs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }


}