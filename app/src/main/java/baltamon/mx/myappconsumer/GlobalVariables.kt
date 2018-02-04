package baltamon.mx.myappconsumer

import android.net.Uri
/**
 * Created by Baltazar Rodriguez on 01/02/2018.
 */
// TABLE COLUMNS
val FRIEND_ID = "_id"
val FRIEND_NAME = "friendName"
val FRIEND_PHONE = "friendPhone"
val FRIEND_CREATED_ON = "friendCreationOn"

val AUTHORITY = "baltamon.mx.myappprovider" // You can find this in the Manifest
val BASE_PATH = "friends" // Table name

val CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH)

val FRIEND_OBJECT = "friend_object"