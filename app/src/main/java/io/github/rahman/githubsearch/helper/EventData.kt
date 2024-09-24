package io.github.rahman.githubsearch.helper

open class EventData<out T>(val content: T? = null,
                            val message: String = "OK") {

    private var hasBeenHandled = false
    fun getContentIfNotHandled(): Boolean {
        return if (hasBeenHandled) {
            false
        } else {
            hasBeenHandled = true
            true
        }
    }
}