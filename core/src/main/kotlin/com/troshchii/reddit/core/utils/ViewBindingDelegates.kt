package com.troshchii.reddit.core.utils

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

public  inline fun <VIEW_BINDING : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> VIEW_BINDING
): Lazy<VIEW_BINDING> {
    return lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}

public fun <VIEW_BINDING> Fragment.viewBinding(): ReadWriteProperty<Fragment, VIEW_BINDING> {
    return object : ReadWriteProperty<Fragment, VIEW_BINDING>, DefaultLifecycleObserver {
        private var binding: VIEW_BINDING? = null

        init {
            this@viewBinding
                .viewLifecycleOwnerLiveData
                .observe(this@viewBinding, { owner: LifecycleOwner? ->
                    owner?.lifecycle?.addObserver(this)
                })
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): VIEW_BINDING {
            return binding ?: error("Called before onCreateView or after onDestroyView.")
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: VIEW_BINDING) {
            binding = value
        }
    }
}
