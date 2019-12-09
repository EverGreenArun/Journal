package com.arun.journal.data

import android.os.Parcel
import android.os.Parcelable

data class JournalResponse(val title:String?, val rows:ArrayList<Journal>)

data class Journal(val title:String?, val description:String?, val imageHref:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageHref)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Journal> {
        override fun createFromParcel(parcel: Parcel): Journal {
            return Journal(parcel)
        }

        override fun newArray(size: Int): Array<Journal?> {
            return arrayOfNulls(size)
        }
    }
}
