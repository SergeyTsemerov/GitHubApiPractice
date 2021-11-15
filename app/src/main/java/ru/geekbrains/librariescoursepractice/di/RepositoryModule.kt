package ru.geekbrains.librariescoursepractice.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.librariescoursepractice.App
import ru.geekbrains.librariescoursepractice.database.DataBase
import ru.geekbrains.librariescoursepractice.database.INetworkStatus
import ru.geekbrains.librariescoursepractice.database.IRepositoriesCache
import ru.geekbrains.librariescoursepractice.database.RoomRepositoriesCache
import ru.geekbrains.librariescoursepractice.model.IDataSource
import ru.geekbrains.librariescoursepractice.model.IGitHubRepositoriesRepo
import ru.geekbrains.librariescoursepractice.model.RetrofitGithubRepositoriesRepo

@Module
open class RepositoryModule {

    @Provides
    fun reposCache(database: DataBase): IRepositoriesCache = RoomRepositoriesCache(database)

    @RepositoryScope
    @Provides
    fun reposRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGitHubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}