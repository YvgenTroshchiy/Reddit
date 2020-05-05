package com.troshchii.reddit.domain

import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.functional.Either


//TODO: Update
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun execute(params: Params? = null): Either<Failure, Type>

    class None

}