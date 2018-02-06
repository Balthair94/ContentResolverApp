package baltamon.mx.myappconsumer

import android.app.LoaderManager
import android.content.ContentValues
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.refresh_list -> restartLoader()
        }
        return super.onOptionsItemSelected(item)
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
        val delCount = contentResolver.delete(CONTENT_URI, FRIEND_ID + "=" + id, null)
        if (delCount > 0){
            restartLoader()
            showToast("We are not friends anymore :(")
        } else {
            showToast("Record no deleted")
        }
    }

    override fun onFriendUpdated(friend: Friend) {
        val values = ContentValues()
        val stringArgs = arrayOf(friend.id.toString())
        values.put(FRIEND_NAME, friend.friendName)
        values.put(FRIEND_PHONE, friend.friendPhone)
        val updCount = contentResolver.update(CONTENT_URI, values, FRIEND_ID + "=?", stringArgs)

        if(updCount > 0) {
            restartLoader()
            showToast("Friend Updated")
        } else {
            showToast("Record no updated")
        }
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
