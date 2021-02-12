package mobi.muntean.smalltalk.ui.chat

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import mobi.muntean.smalltalk.Message
import mobi.muntean.smalltalk.R


class ChatAdapter(private val dataSet: MutableList<Message>, private val userName: String) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView
        val message: TextView

        init {
            // Define click listener for the ViewHolder's View.
            userName = view.findViewById(R.id.username)
            message = view.findViewById(R.id.message)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        var view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.message_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        val userName = dataSet[position].userName
        val message = dataSet[position].message

        viewHolder.userName.text = userName
        viewHolder.message.text = message

        /*val constraintSet = ConstraintSet()
        constraintSet.clone(viewHolder.messageContainerParent)
        val layoutParams: ConstraintLayout.LayoutParams = viewHolder.messageContainer.getLayoutParams() as ConstraintLayout.LayoutParams

        if(userName == this.userName){
            viewHolder.userName.setTextColor(Color.BLUE)

            layoutParams.endToEnd = viewHolder.itemView.id
            viewHolder.messageContainer.setLayoutParams(layoutParams)
        }else {
            viewHolder.userName.setTextColor(Color.GREEN)
            layoutParams.startToStart = viewHolder.itemView.id
            viewHolder.messageContainer.setLayoutParams(layoutParams)
        }*/


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
