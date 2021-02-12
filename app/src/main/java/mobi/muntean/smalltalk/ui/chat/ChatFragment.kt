package mobi.muntean.smalltalk.ui.chat

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_chat.*
import mobi.muntean.smalltalk.MainViewModel
import mobi.muntean.smalltalk.Message
import mobi.muntean.smalltalk.Messages
import mobi.muntean.smalltalk.R

class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel
    private val model: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_left)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatViewModel =
            ViewModelProvider(this).get(ChatViewModel::class.java)
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getChatMessages()
        writeMessage()
    }

    fun getChatMessages() {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "https://us-central1-smalltalk-3bfb8.cloudfunctions.net/messages"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val result = Messages.fromJson(response)

                val messages = mutableListOf<Message>()

                for (message in result) {
                    messages.add(message)
                }

                val adapter = ChatAdapter(messages, model.userName ?: "")
                chat_recyclerView.layoutManager = LinearLayoutManager(context);
                chat_recyclerView.adapter = adapter
                chat_recyclerView.scrollToPosition(messages.size-1)
            },
            {

            })

        queue.add(stringRequest)
    }

    private fun writeMessage(){
        send_message_button.setOnClickListener {
            val message = message_editText.text.toString()

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(context)
            val url = "https://us-central1-smalltalk-3bfb8.cloudfunctions.net/messages?userId=nby86fy&userName=${model.userName}&message=$message"

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.POST, url,
                { response ->
                    message_editText.setText("")
                    getChatMessages()
                },
                {
                    message_editText.setText("")
                    getChatMessages()
                })

            queue.add(stringRequest)
        }
    }
}