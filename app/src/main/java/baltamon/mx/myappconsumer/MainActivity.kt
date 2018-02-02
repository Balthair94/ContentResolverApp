package baltamon.mx.myappconsumer

import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

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
}
