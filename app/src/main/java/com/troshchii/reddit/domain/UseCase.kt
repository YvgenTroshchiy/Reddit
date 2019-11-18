package com.troshchii.reddit.domain


abstract class UseCase<out Type, in Param> where Type : Any {

    abstract suspend fun execute(param: Param): Type

    abstract suspend fun execute(param1: Param, param2: Param): Type

}