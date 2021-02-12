package mobi.muntean.smalltalk.login

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import mobi.muntean.smalltalk.Messages
import mobi.muntean.smalltalk.User

class LoginModel(val loginListener: LoginListener) {

    fun login(username: String, password: String, context: Context) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "https://us-central1-smalltalk-3bfb8.cloudfunctions.net/login?username=$username&password=$password"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val user = User.fromJson(response)

                user?.let {
                    loginListener.onLogin(it)
                }
            },
            {
                when(it.networkResponse.statusCode){
                    500 -> {
                        Log.d("LOGIN_FAILED", "Vi har problemer med serveren...")
                        loginListener.onLoginError(500)
                    }
                    else -> {
                        Log.d("LOGIN_FAILED", "Vi aner ikke hva som gikk galt...")
                    }
                }
            })

        queue.add(stringRequest)
    }


    /*fun convertJsonToMessages(context: Context) {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "https://us-central1-smalltalk-3bfb8.cloudfunctions.net/messages"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val result = Messages.fromJson(response)

                for (message in result) {

                }
            },
            {

            })

        queue.add(stringRequest)
    }*/
}