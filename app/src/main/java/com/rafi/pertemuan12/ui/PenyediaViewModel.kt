package com.rafi.pertemuan12.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rafi.pertemuan12.MahasiswaApplications
import com.rafi.pertemuan12.ui.home.viewmodel.HomeViewModel
import com.rafi.pertemuan12.ui.insert.viewmodel.InsertViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                mahasiswaApp().container.repositoryMhs
            )
        }

        initializer {
            InsertViewModel(
                mahasiswaApp().container.repositoryMhs
            )
        }
    }
}

fun CreationExtras.mahasiswaApp(): MahasiswaApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)