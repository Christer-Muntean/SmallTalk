package mobi.muntean.smalltalk

import com.beust.klaxon.*
import java.io.Serializable

private val klaxon = Klaxon()

data class User(
    val id: String,
    val userName: String,
    val firstName: String
) {
    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<User>(json)
    }
}
