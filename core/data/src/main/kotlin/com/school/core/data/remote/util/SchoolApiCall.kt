package com.school.core.data.remote.util

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.school.core.data.remote.exception.ExpiredTokenException
import com.school.core.data.remote.exception.FailRefreshException
import com.school.core.data.remote.exception.NeedSignInException
import com.school.core.domain.exception.ConflictDataException
import com.school.core.domain.exception.InvalidTokenException
import com.school.core.domain.exception.NeedTokenException
import com.school.core.domain.exception.NotFoundException
import com.school.core.domain.exception.ServerErrorException
import com.school.core.domain.exception.TooManyRequestException
import com.school.core.domain.exception.UnKnownHttpException
import com.school.core.domain.exception.WrongDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

data class Error(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
)

suspend inline fun <T> schoolApiCall(
    crossinline callFunction: suspend () -> T,
): T =
    try {
        withContext(Dispatchers.IO) {
            callFunction()
        }
    } catch (e: HttpException) {
        val error = e.getError() ?: Error("", e.code())
        throw when (error.status) {
            400 -> WrongDataException(error.message)
            401 -> InvalidTokenException(error.message)
            404 -> NotFoundException(error.message)
            409 -> ConflictDataException(error.message)
            429 -> TooManyRequestException(error.message)
            in 500..600 -> ServerErrorException(error.message)
            else -> UnKnownHttpException(error.message)
        }
    } catch (e: NeedSignInException) {
        throw NeedTokenException()
    } catch (e: ExpiredTokenException) {
        throw NeedTokenException()
    } catch (e: FailRefreshException) {
        throw NeedTokenException()
    }

fun <T> Response<T>.errorHandling() {
    if (!isSuccessful) {
        val error = errorBody()?.let { Gson().fromJson(it.string(), Error::class.java) }
        error?.let {
            throw when (error.status) {
                400 -> WrongDataException(error.message)
                401 -> InvalidTokenException(error.message)
                404 -> NotFoundException(error.message)
                409 -> ConflictDataException(error.message)
                429 -> TooManyRequestException(error.message)
                in 500..600 -> ServerErrorException(error.message)
                else -> UnKnownHttpException(error.message)
            }
        }
    }
}

fun HttpException.getError(): Error? =
    response()?.errorBody()?.let { Gson().fromJson(it.string(), Error::class.java) }