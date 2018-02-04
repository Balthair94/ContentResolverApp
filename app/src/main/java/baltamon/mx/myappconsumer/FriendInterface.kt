package baltamon.mx.myappconsumer

/**
 * Created by Baltazar Rodriguez on 03/02/2018.
 */
interface FriendInterface {
    fun onFriendClick(friend: Friend)
    fun onFriendDeleted(id: Int)
    fun onFriendUpdated(friend: Friend)
}