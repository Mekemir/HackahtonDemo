package com.example.hackahtondemo.util.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hackahtondemo.fragments.entrypage.EntryViewModel
import com.example.hackahtondemo.fragments.resultspage.ResultsViewModel
import com.example.hackahtondemo.util.callback.CustomCallback

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    val app: Application,
    val callback: CustomCallback? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(EntryViewModel::class.java) -> {
                return EntryViewModel(app) as T
            }
            modelClass.isAssignableFrom(ResultsViewModel::class.java) -> {
                return ResultsViewModel(app, callback) as T
            }
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}