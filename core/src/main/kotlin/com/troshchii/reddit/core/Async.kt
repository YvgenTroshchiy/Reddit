package com.troshchii.reddit.core

import java.util.Arrays

/**
 * Async is a state representation of asynchronous operations
 * Uninitialized
 * Loading
 * Success
 * Fail - has an error property to retrieve the failure.
 *
 * The T generic is unused for some classes but since it is sealed and useful for Success and Fail,
 * it should be on all of them.
 *
 * Complete: Success, Fail
 * ShouldLoad: Uninitialized, Fail
 */
sealed class Async<out T>(val complete: Boolean, val shouldLoad: Boolean, private val value: T?) {

    /**
     * Returns the value or null.
     *
     * Success always have a value. Loading and Fail can also return a value which is useful for
     * pagination or progressive data loading.
     *
     * Can be invoked as an operator like: `yourProp()`
     */
    open operator fun invoke(): T? = value
}

object Uninitialized :
    Async<Nothing>(complete = false, shouldLoad = true, value = null),
    Incomplete

data class Loading<out T>(private val value: T? = null) :
    Async<T>(complete = false, shouldLoad = false, value = value),
    Incomplete

data class Success<out T>(private val value: T) : Async<T>(complete = true, shouldLoad = false, value = value) {

    override operator fun invoke(): T = value
}

data class Fail<out T>(val error: Throwable, private val value: T? = null) : Async<T>(complete = true, shouldLoad = true, value = value) {
    override fun equals(other: Any?): Boolean {
        if (other !is Fail<*>) return false

        val otherError = other.error
        return error::class == otherError::class &&
                error.message == otherError.message &&
                error.stackTrace.firstOrNull() == otherError.stackTrace.firstOrNull()
    }

    override fun hashCode(): Int = Arrays.hashCode(arrayOf(error::class, error.message, error.stackTrace[0]))
}

/**
 * Helper interface for using Async in a when clause for handling both Uninitialized and Loading.
 *
 * With this, you can do:
 * when (data) {
 *     is Incomplete -> Unit
 *     is Success    -> Unit
 *     is Fail       -> Unit
 * }
 */
interface Incomplete

//fun <L, R> Either<L, R>.toAsync(): Async<R> {
//    return when (this) {
//        is Either.Left -> Fail(left)
//        is Either.Right -> Success(data)
//    }
//}

//TODO: replace with Either
fun <T> NetworkResult<T>.toAsync(): Async<T> {
    return when (this) {
        is NetworkResult.Error -> Fail(exception)
        is NetworkResult.Success -> Success(data)
    }
}

sealed class NetworkResult<out T> {
    data class Error(val exception: Throwable) : NetworkResult<Nothing>()
    data class Success<out T>(val data: T) : NetworkResult<T>()
}