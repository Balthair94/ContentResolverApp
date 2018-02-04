package baltamon.mx.myappconsumer

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by Baltazar Rodriguez on 01/02/2018.
 */
class FriendsCursorAdapter(context: Context, cursor: Cursor?, flags: Int): CursorAdapter(context, cursor, flags) {

    var listener: FriendInterface? = null
    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View =
            LayoutInflater.from(p0).inflate(R.layout.layout_item_list, p2, false)

    override fun bindView(p0: View?, p1: Context?, p2: Cursor) {
        listener = p1 as FriendInterface
        val friend = Friend(
                p2.getInt(p2.getColumnIndex(FRIEND_ID)),
                p2.getString(p2.getColumnIndex(FRIEND_NAME)),
                p2.getString(p2.getColumnIndex(FRIEND_PHONE)),
                p2.getString(p2.getColumnIndex(FRIEND_CREATED_ON)))

        val tvName = p0?.findViewById<TextView>(R.id.tv_name)
        val tvPhone = p0?.findViewById<TextView>(R.id.tv_phone)

        tvName?.text = friend.friendName
        tvPhone?.text = friend.friendPhone

        val layoutItem = p0?.findViewById<LinearLayout>(R.id.ll_item)
        layoutItem?.setOnClickListener {
            listener?.onFriendClick(friend)
        }
    }
}