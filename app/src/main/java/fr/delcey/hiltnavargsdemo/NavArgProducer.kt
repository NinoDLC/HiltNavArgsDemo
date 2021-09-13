package fr.delcey.hiltnavargsdemo

import androidx.collection.ArrayMap
import androidx.lifecycle.SavedStateHandle
import java.lang.reflect.Method
import javax.inject.Inject

class NavArgProducer @Inject constructor(private val savedStateHandle: SavedStateHandle) {

    companion object {
        // Saves references to reflected Methods, because reflection can be costly
        private val methodMap = ArrayMap<String, Method>()
    }

    /**
     * Inspired from [androidx.navigation.NavArgsLazy]. Should its implementation change,
     * this method should change accordingly
     */
    fun <T> getNavArgs(clazz: Class<T>): T {
        val method = methodMap.getOrPut(clazz.canonicalName) { clazz.getMethod("fromSavedStateHandle", SavedStateHandle::class.java) }

        @Suppress("UNCHECKED_CAST")
        return method.invoke(null, savedStateHandle) as T
    }
}
