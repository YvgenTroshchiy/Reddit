package com.troshchii.reddit.domain


//abstract class UseCase<out Type, in Params> where Type : Any {


//TODO: Update
abstract class UseCase {

//    abstract suspend fun run(params: Params): Type

    abstract suspend fun execute(): Any
}