package com.rafi.pertemuan12.ui.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafi.pertemuan12.model.Mahasiswa
import com.rafi.pertemuan12.repository.NetworkRepositoryMhs
import com.rafi.pertemuan12.repository.RepositoryMhs
import com.rafi.pertemuan12.ui.home.viewmodel.HomeuiState
import com.rafi.pertemuan12.ui.insert.viewmodel.InsertUiState
import com.rafi.pertemuan12.ui.insert.viewmodel.MahasiswaEvent
import com.rafi.pertemuan12.ui.navigation.DestinasiDetail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    val uiState: StateFlow<DetailuiState> =
        repositoryMhs.getMhs(_nim)
            .filterNotNull()
            .map {
                DetailuiState(addEvent = it.toDetailKontak())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailuiState()
            )
}

data class DetailuiState(
    val addEvent: MahasiswaEvent = MahasiswaEvent()
)

fun Mahasiswa.toDetailKontak(): MahasiswaEvent =
    MahasiswaEvent(
        nim = nim,
        nama = nama,
        jenisKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan,
        judulSkripsi = judulSkripsi,
        dospem1 = dospem1,
        dospem2 = dospem2
    )
