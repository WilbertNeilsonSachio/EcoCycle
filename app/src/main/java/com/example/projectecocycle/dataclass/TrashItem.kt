package com.example.projectecocycle.dataclass

import android.os.Parcel
import android.os.Parcelable

data class TrashItem(
    val imageResId: Int,
    val name: String,
    val price: String,
    val unit: String,
    val tag: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResId)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(unit)
        parcel.writeString(tag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrashItem> {
        override fun createFromParcel(parcel: Parcel): TrashItem {
            return TrashItem(parcel)
        }

        override fun newArray(size: Int): Array<TrashItem?> {
            return arrayOfNulls(size)
        }
    }
}