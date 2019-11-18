package com.troshchii.reddit.domain


abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun execute(params: Params): Type

}