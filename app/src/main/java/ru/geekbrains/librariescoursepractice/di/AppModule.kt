package ru.geekbrains.librariescoursepractice.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.librariescoursepractice.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}