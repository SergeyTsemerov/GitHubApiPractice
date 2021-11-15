package ru.geekbrains.librariescoursepractice.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.librariescoursepractice.App
import ru.geekbrains.librariescoursepractice.database.DataBase
import ru.geekbrains.librariescoursepractice.database.INetworkStatus
import ru.geekbrains.librariescoursepractice.database.IUserCache
import ru.geekbrains.librariescoursepractice.database.RoomUserCache
import ru.geekbrains.librariescoursepractice.model.IDataSource
import ru.geekbrains.librariescoursepractice.model.IGitHubUsersRepo
import ru.geekbrains.librariescoursepractice.model.RetrofitGithubUsersRepo

@Module
open class UsersModule {

    @UsersScope
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache
    ): IGitHubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Provides
    fun usersCache(database: DataBase): IUserCache = RoomUserCache(database)

    @UsersScope
    @Provides
    open fun scopeContainer(app: App): IUsersScopeContainer = app
}