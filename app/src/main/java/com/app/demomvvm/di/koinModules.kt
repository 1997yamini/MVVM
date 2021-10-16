package com.app.demomvvm.di

import com.app.demomvvm.data.api.AppRetrofit
import com.app.demomvvm.data.repo.MVVMRepo
import com.app.demomvvm.ui.main.viewModel.WebViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    single {
        AppRetrofit.apiServices
    }
    single { MVVMRepo(get()) }
    viewModel { WebViewModel(get()) }

}