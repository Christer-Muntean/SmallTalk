package mobi.muntean.smalltalk

import com.beust.klaxon.*

private val klaxon = Klaxon()

class Messages(elements: Collection<Message>) : ArrayList<Message>(elements) {
    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = Messages(klaxon.parseArray<Message>(json)!!)
    }
}

data class Message (
    @Json(name = "userId")
    val userID: String,

    val userName: String,
    val message: String
)
