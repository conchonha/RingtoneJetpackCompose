package com.example.myapplication.data.data_source.config

import android.app.Application
import android.media.MediaPlayer
import android.nfc.FormatException
import com.example.myapplication.R
import com.example.myapplication.utils.SingleLiveEvent
import com.example.myapplication.utils.Validator
import java.io.IOException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class HandleException private constructor(private val application: Application) : Throwable() {
    private var TAG: String = this.javaClass.name
    val exception = SingleLiveEvent<Pair<String, ExceptionType>>()

    override fun toString(): String {
        return handleNetworkException(cause)?.first ?: super.toString()
    }

    fun handleNetworkException(throwable: Throwable?): Pair<String, ExceptionType>? {
        var type: ExceptionType
        var message: String = ""

        when (throwable) {
            is SocketException -> {
                message = application.getString(R.string.exception_no_internet)
                type = ExceptionType.TYPE_NO_INTERNET
            }
            is TimeoutException -> {
                message = application.getString(R.string.exception_time_out_error)
                type = ExceptionType.TYPE_CONNECT_TIMEOUT
            }
            is SecurityException -> {
                type = ExceptionType.TYPE_SECURITY
                message = application.getString(R.string.exception_you_do_not_have_access)
            }
            is FormatException -> {
                message = application.getString(R.string.exception_format_error)
                type = ExceptionType.TYPE_FORMAT_EXCEPTION
            }
            is UnknownHostException -> {
                type = ExceptionType.TYPE_UNKNOWN_HOST
                message = application.getString(R.string.exception_there_is_no_address)
            }
            is IOException -> {
                message = application.getString(R.string.exception_no_internet)
                type = ExceptionType.TYPE_NO_INTERNET
            }
            else -> {
                if (throwable?.message?.contains("is not a subtype of") == true) {
                    message = application.getString(R.string.exception_value_is_not_a_type_of_data)
                    type = ExceptionType.TYPE_UNABLE_TO_PROCESS
                } else {
                    return null
                }
            }
        }

        val err = "message: $message\n"
        exception.postValue(Pair(err, type))
        return Pair(err, type)
    }

    fun checkNetwork(): Boolean {
        if (!Validator.isInternetAvailable(application)) {
            exception.postValue(
                Pair(
                    application.getString(R.string.exception_no_internet),
                    ExceptionType.TYPE_NO_INTERNET
                )
            )
            return false
        }
        return true
    }

    fun handleExceptionNetwork(statusCode: Int): Pair<String, ExceptionType>? {
        var type: ExceptionType
        var message: String = ""

        when (statusCode) {
            ExceptionCode.STATUS_CODE_400 -> {
                message = application.getString(R.string.exception_unauthorised_request)
                type = ExceptionType.TYPE_400
            }
            ExceptionCode.STATUS_CODE_401 -> {
                message = application.getString(R.string.authorization_required)
                type = ExceptionType.TYPE_401
            }
            ExceptionCode.STATUS_CODE_403 -> {
                message = application.getString(R.string.you_dont_have_permission_to_access)
                type = ExceptionType.TYPE_403
            }
            ExceptionCode.STATUS_CODE_404 -> {
                type = ExceptionType.TYPE_404
                message = application.getString(R.string.not_found_url)
            }
            ExceptionCode.STATUS_CODE_409 -> {
                message = application.getString(R.string.exception_error_due_to_a_conflict)
                type = ExceptionType.TYPE_409
            }
            ExceptionCode.STATUS_CODE_408 -> {
                message = application.getString(R.string.exception_connection_request_timeout)
                type = ExceptionType.TYPE_408
            }
            ExceptionCode.STATUS_CODE_500 -> {
                message = application.getString(R.string.exception_internal_server)
                type = ExceptionType.TYPE_500
            }
            ExceptionCode.STATUS_CODE_503 -> {
                message = application.getString(R.string.exception_service_unavailable)
                type = ExceptionType.TYPE_503
            }
            else -> return null
        }

        val err =
            "${application.getString(R.string.statusCode)}: $statusCode\n ${application.getString(R.string.message)}: $message\n"
        exception.postValue(Pair(err, type))
        return Pair(err, type)
    }

    fun handleMessageMediaPlayError(errorType: Int): Pair<String, ExceptionType>? {
        var message = when (errorType) {
            MediaPlayer.MEDIA_ERROR_IO -> R.string.media_network_or_file_error
            MediaPlayer.MEDIA_ERROR_UNKNOWN -> R.string.media_error_unknow
            MediaPlayer.MEDIA_ERROR_SERVER_DIED -> R.string.media_error_server_died
            MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK -> R.string.media_error_not_valid
            MediaPlayer.MEDIA_ERROR_UNSUPPORTED,
            MediaPlayer.MEDIA_ERROR_MALFORMED -> R.string.media_error_malformed
            MediaPlayer.MEDIA_ERROR_TIMED_OUT -> R.string.media_time_out
            else -> return null
        }

        val pair = Pair(application.getString(message), ExceptionType.TYPE_MEDIA_PLAYER)
        exception.postValue(pair)
        return pair
    }

    companion object {
        private var instance: HandleException? = null

        fun getInstance(application: Application) = synchronized(this) {
            instance ?: HandleException(application).also { instance = it }
        }
    }
}

enum class ExceptionType {
    TYPE_UNKNOWN_HOST, TYPE_SECURITY, TYPE_CONNECT_TIMEOUT, TYPE_DEFAULT, TYPE_NULL_THROWN_ERROR, TYPE_NO_INTERNET, TYPE_UNEXPECTED_ERROR, TYPE_FORMAT_EXCEPTION, TYPE_UNABLE_TO_PROCESS, TYPE_400, TYPE_401, TYPE_403, TYPE_404, TYPE_409, TYPE_408, TYPE_500, TYPE_503, TYPE_MEDIA_PLAYER
}

private object ExceptionCode {
    const val STATUS_CODE_400 = 400
    const val STATUS_CODE_401 = 401
    const val STATUS_CODE_403 = 403
    const val STATUS_CODE_404 = 404
    const val STATUS_CODE_409 = 409
    const val STATUS_CODE_408 = 408
    const val STATUS_CODE_500 = 500
    const val STATUS_CODE_503 = 503
}