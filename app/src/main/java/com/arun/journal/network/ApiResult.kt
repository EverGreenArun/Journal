package com.arun.journal.network

import retrofit2.Response

/**
 * base class to hold api result
 * */
sealed class ApiResult<out T : Any>

/**
 * base class to hold api success data
 * */
class ApiSuccess<out T : Any>(val data: T) : ApiResult<T>()

/**
 * base class to hold api Error data
 * */
class ApiError(val message: String = "", val code: String = "") :
    ApiResult<Nothing>()


/**
 * Helper class to handle api result
 * */
object ResultHelper {
    private const val UN_KNOW_ERROR = "Something went wrong"

    /**
     * method handles api response
     * @return ApiSuccess or ApiError
     * */
    fun <T : Any> handleResult(response: Response<T>): ApiResult<T> {
        return try {
            if (response.isSuccessful) {
                ApiSuccess(response.body() as T)
            } else {
                ApiError(UN_KNOW_ERROR)
            }
        } catch (e: Exception) {
            ApiError(UN_KNOW_ERROR)
        }
    }

    /**
     * method handles api response
     * */
    fun getDefaultError() = ApiError(UN_KNOW_ERROR)
}