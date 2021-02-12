package mobi.muntean.smalltalk.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import mobi.muntean.smalltalk.MainActivity
import mobi.muntean.smalltalk.Messages
import mobi.muntean.smalltalk.R
import mobi.muntean.smalltalk.User

class LoginActivity : AppCompatActivity(), LoginListener {

    lateinit var loginModel: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //loginModel = LoginModel(this)

        //initializeUi()

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("userName", "testUser")
        }
        startActivity(intent)

    }


    private fun initializeUi(){
        login_button.setOnClickListener {
            if(!username_editText.text.isNullOrBlank() && !password_editText.text.isNullOrBlank()){
                loginModel.login(
                    username_editText.text.toString(),
                    password_editText.text.toString(),
                    this)
            }
        }
    }

    override fun onLogin(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("userName", user.userName)
        }
        startActivity(intent)
    }

    override fun onLoginError(statusCode: Int) {
        when(statusCode){
            500 -> {
                Toast.makeText(this, "Brukeren eksisterer ikke", Toast.LENGTH_LONG).show()
            }
        }
    }
}