package com.troshchii.reddit.core.vm

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)