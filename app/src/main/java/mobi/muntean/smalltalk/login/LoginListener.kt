package mobi.muntean.smalltalk.login

import mobi.muntean.smalltalk.User

interface LoginListener {
    fun onLogin(user: User)
    fun onLoginError(statusCode: Int)
}