package fr.delcey.hiltnavargsdemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Observes a [LiveData] until the `block` is done executing.
 */
fun <T> LiveData<T>.observeForTesting(block: (LiveData<T>) -> Unit) {
    val observer = Observer<T> { }
    try {
        observeForever(observer)
        block(this)
    } finally {
        removeObserver(observer)
    }
}