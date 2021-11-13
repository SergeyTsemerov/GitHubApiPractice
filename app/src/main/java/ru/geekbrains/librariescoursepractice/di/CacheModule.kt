package ru.geekbrains.librariescoursepractice.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.librariescoursepractice.App
import ru.geekbrains.librariescoursepractice.database.*
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): DataBase =
        Room.databaseBuilder(app, DataBase::class.java, DataBase.DB_NAME)
            .build()

    @Singleton
    @Provides
    fun usersCache(database: DataBase): IUserCache = RoomUserCache(database)

    @Singleton
    @Provides
    fun reposCache(database: DataBase): IRepositoriesCache = RoomRepositoriesCache(database)
}