package baltamon.mx.myappconsumer

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView

/**
 * Created by Baltazar Rodriguez on 01/02/2018.
 */
class FriendsCursorAdapter(context: Context, cursor: Cursor?, flags: Int): CursorAdapter(context, cursor, flags) {

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View =
            LayoutInflater.from(p0).inflate(R.layout.layout_item_list, p2, false)

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {
        val friendName = p2?.getString(p2.getColumnIndex(FRIEND_NAME))
        val friendPhone = p2?.getString(p2.getColumnIndex(FRIEND_PHONE))
        val tvName = p0?.findViewById<TextView>(R.id.tv_name)
        val tvPhone = p0?.findViewById<TextView>(R.id.tv_phone)

        tvName?.text = friendName
        tvPhone?.text = friendPhone
    }
}