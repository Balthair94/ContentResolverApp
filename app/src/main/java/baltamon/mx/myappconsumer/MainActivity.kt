package baltamon.mx.myappconsumer

import android.app.LoaderManager
import android.content.ContentValues
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor>, FriendInterface {

    var cursorAdapter: FriendsCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        initializeList()
    }

    fun initializeList(){
        cursorAdapter = FriendsCursorAdapter(this, null, 0)
        list_view_friends.adapter = cursorAdapter
        restartLoader()
    }

    fun setupToolbar() {
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Consumer Friends List"
    }

    fun restartLoader() {
        loaderManager.restartLoader(0, null, this)
    }

    override fun onLoadFinished(p0: Loader<Cursor>?, p1: Cursor?) {
        cursorAdapter?.swapCursor(p1)
    }

    override fun onLoaderReset(p0: Loader<Cursor>?) {
        cursorAdapter?.swapCursor(null)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> =
            CursorLoader(this, CONTENT_URI, null, null, null, null)

    override fun onFriendClick(friend: Friend) {
        val dialog = FriendDetailDialogFragment.newInstance(friend)
        dialog.show(supportFragmentManager, "fragment_friend_detail")
    }

    override fun onFriendDeleted(id: Int) {
        contentResolver.delete(CONTENT_URI, FRIEND_ID + "=" + id, null)
        restartLoader()
        showToast("We are not friend anymore :(")
    }

    override fun onFriendUpdated(friend: Friend) {
        val values = ContentValues()
        values.put(FRIEND_NAME, friend.friendName)
        values.put(FRIEND_PHONE, friend.friendPhone)
        contentResolver.update(CONTENT_URI, values, FRIEND_ID + "=" + friend.id, null)
        restartLoader()
        showToast("Friend Updated")
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
