package project.elizavetamikhailova.bguv2.view.uimodels

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Category(@PrimaryKey(autoGenerate = true)
                       var id: Int,
                       @SerializedName("Tables_in_e49426_db")
                       @Expose
                       var tablesInBgu: String? = null,
                       @SerializedName("real_table_name")
                       @Expose
                       var realTableName: String? = null) : Parcelable, BaseObservable() {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(tablesInBgu)
        parcel.writeString(realTableName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}