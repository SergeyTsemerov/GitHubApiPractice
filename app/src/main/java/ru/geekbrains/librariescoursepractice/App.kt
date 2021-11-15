package ru.geekbrains.librariescoursepractice

import android.app.Application
import ru.geekbrains.librariescoursepractice.di.*

class App : Application(), IUsersScopeContainer, IRepositoryScopeContainer {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var usersSubcomponent: UsersSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUsersSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun initRepositorySubcomponent() = usersSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseUsersScope() {
        usersSubcomponent = null
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }
}