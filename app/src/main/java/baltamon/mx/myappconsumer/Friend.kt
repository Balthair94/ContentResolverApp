package baltamon.mx.myappconsumer

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Baltazar Rodriguez on 03/02/2018.
 */
class Friend(val id: Int,
             val friendName: String,
             val friendPhone: String,
             val friendCreationOn: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(friendName)
        parcel.writeString(friendPhone)
        parcel.writeString(friendCreationOn)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Friend> {
        override fun createFromParcel(parcel: Parcel): Friend {
            return Friend(parcel)
        }

        override fun newArray(size: Int): Array<Friend?> {
            return arrayOfNulls(size)
        }
    }
}