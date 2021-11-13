package ru.geekbrains.librariescoursepractice

import android.app.Application
import ru.geekbrains.librariescoursepractice.di.AppComponent
import ru.geekbrains.librariescoursepractice.di.AppModule
import ru.geekbrains.librariescoursepractice.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}