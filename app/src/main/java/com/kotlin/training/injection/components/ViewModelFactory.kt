package com.kotlin.training.injection.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


//@Singleton
 class ViewModelFactory @Inject constructor(creators: MutableMap<Class<out ViewModel?>?, @JvmSuppressWildcards Provider<ViewModel?>?>?) : ViewModelProvider.Factory {
    private val creators: MutableMap<Class<out ViewModel?>?, Provider<ViewModel?>?>?
    init {
        this.creators = creators
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel?>? = creators!![modelClass]
        if (creator == null) { // if the viewmodel has not been created
// loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for (entry in creators.entries) { // if it's allowed, set the Provider<ViewModel>
                if (modelClass!!.isAssignableFrom(entry.key)) {
                    creator = entry.value
                    break
                }
            }
        }
        // if this is not one of the allowed keys, throw exception
        requireNotNull(creator) { "unknown model class $modelClass" }
        // return the Provider
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}


