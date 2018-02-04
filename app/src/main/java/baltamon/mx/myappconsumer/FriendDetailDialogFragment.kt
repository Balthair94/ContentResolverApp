package baltamon.mx.myappconsumer

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_fragment_details.*

/**
 * Created by Baltazar Rodriguez on 03/02/2018.
 */
class FriendDetailDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(friend: Friend): FriendDetailDialogFragment {
            val dialog = FriendDetailDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(FRIEND_OBJECT, friend)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.dialog_fragment_details, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        btn_cancel.setOnClickListener { dismiss() }
        fillDialog(arguments.getParcelable(FRIEND_OBJECT))
    }

    fun fillDialog(friend: Friend){
        et_friend_id.setText(friend.id.toString())
        et_friend_name.setText(friend.friendName)
        et_friend_phone.setText(friend.friendPhone)
        et_friend_created_on.setText(friend.friendCreationOn)
    }
}