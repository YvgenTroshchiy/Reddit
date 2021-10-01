package com.troshchii.reddit.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    object NetworkConnection : Failure()
    class ServerError(val code: Int? = null, val message: String) : Failure()

    /** Extend this class for feature specific failures */
    abstract class FeatureFailure : Failure()

}
