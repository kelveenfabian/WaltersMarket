package com.v.waltersmarket.ui.viewmodel

import com.v.waltersmarket.data.repository.AstrologyRepositoryFactory

class AstrologyViewModelFactory {

    fun getAstrologyViewModel() : AstrologyViewModel {
        return AstrologyViewModel(
            AstrologyRepositoryFactory.getAstrologyRepository()
        )
    }
}