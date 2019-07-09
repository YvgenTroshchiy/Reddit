package com.troshchii.reddit.domain


//TODO: Update
abstract class UseCase {
    abstract suspend fun execute(): Any
}