package com.rafi.pertemuan12.di

import com.google.firebase.firestore.FirebaseFirestore
import com.rafi.pertemuan12.repository.NetworkRepositoryMhs
import com.rafi.pertemuan12.repository.RepositoryMhs

interface AppContainer{
    val repositoryMhs: RepositoryMhs
}

class MahasiswaContainer: AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkRepositoryMhs(firestore)
    }
}