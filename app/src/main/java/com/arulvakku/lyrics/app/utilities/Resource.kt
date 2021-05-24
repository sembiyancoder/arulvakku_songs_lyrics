package com.arulvakku.lyrics.app.utilities

/**
 * Maintain status of the response{Resource}
 * @status - service status like loading, success OR error
 * @data - Response data
 * @message - you can pass your comment ex: error msgs
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    // class to maintain status of the function
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> { // emit it when response is success
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(
            message: String,
            data: T? = null
        ): Resource<T> { // emit it when response is failed
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> { // emit it before calling service to intimate loading process
            return Resource(Status.LOADING, data, null)
        }
    }
}