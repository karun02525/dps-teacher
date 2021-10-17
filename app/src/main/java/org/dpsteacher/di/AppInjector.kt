package org.dpsteacher.di


import org.dpsteacher.network.RestClient
import org.dpsteacher.network.RestClient.webServices
import org.dpsteacher.mvvm.DashboardViewModel
import org.dpsteacher.mvvm.ProfileViewModel
import org.dpsteacher.ui.authenticaton.AuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    single { RestClient }
    single { webServices() }
}

val viewModelModule = module {
    viewModel {
        AuthViewModel(get())
    }
    viewModel {
        ProfileViewModel(get())
    }
    viewModel {
        DashboardViewModel(get())
    }
}