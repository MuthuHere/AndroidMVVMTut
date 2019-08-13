package com.muthu.androidmvvm.appapplication

import android.app.Application
import com.muthu.androidmvvm.data.db.AppDatabse
import com.muthu.androidmvvm.data.network.MyApi
import com.muthu.androidmvvm.data.network.NetworkInterceptor
import com.muthu.androidmvvm.data.repository.QuotesRepository
import com.muthu.androidmvvm.data.repository.UserRepository
import com.muthu.androidmvvm.ui.profile.ProfileViewModelFactory
import com.muthu.androidmvvm.ui.quotes.QuotesViewModelFactory
import com.muthu.androidmvvm.ui.viewmodel.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppApplication))

        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabse(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }

        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesRepository(instance(), instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
    }
}