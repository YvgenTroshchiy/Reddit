package com.troshchii.reddit.core.functional

sealed class Either<out L, out R> {

    /** Failure in the functional world */
    data class Left<out L>(val left: L) : Either<L, Nothing>()

    /** Success in the functional world */
    data class Right<out R>(val right: R) : Either<Nothing, R>()

    fun <T> fold(foldLeft: (L) -> T, foldRight: (R) -> T): T {
        return when (this) {
            is Left -> foldLeft(left)
            is Right -> foldRight(right)
        }
    }
}