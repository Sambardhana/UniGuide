package com.uniguide.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uniguide.app.data.repository.UniGuideRepository

class UniGuideViewModelFactory(
    private val repository: UniGuideRepository =
        UniGuideRepository()
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(
                UniGuideViewModel::class.java
            )
        ) {
            return UniGuideViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}