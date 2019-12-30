package Mobile.gavelgo.View.Adapter

import Mobile.gavelgo.Model.ChatListModel
import Mobile.gavelgo.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessagesAdapter(internal var context: Context, internal var chatlistModel: ArrayList<ChatListModel>) : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.mychatlist_item, viewGroup, false));
        } else {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.other_chatlist_item, viewGroup, false));
        }

    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.messageTV.setText(chatlistModel.get(position).getMessage())
    }


    override fun getItemViewType(position: Int): Int {

        return if (!chatlistModel.get(position).getUserId().equals("2"))

            VIEW_TYPE_MESSAGE_RECEIVED
        else

            VIEW_TYPE_MESSAGE_SENT
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var messageTV: TextView

        init {

            messageTV = view.findViewById(R.id.messageTV)
        }

    }
}