package fr.delcey.hiltnavargsdemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Observes a [LiveData] until the `block` is done executing.
 */
internal fun <T> LiveData<T>.observeForTesting(block: (LiveData<T>) -> Unit) {
    val observer = Observer<T> { }
    try {
        observeForever(observer)
        block(this)
    } finally {
        removeObserver(observer)
    }
}

inline fun <reified T> assertNonNullLiveDataValue(liveData: LiveData<*>): T = liveData.value.let {
    it ?: throw AssertionError("LiveData value is NULL !")
}.let {
    it as? T ?: throw AssertionError("LiveData value IS NOT of type [ ${T::class.java} ] (current type is = [ ${it::class.java} ])")
}