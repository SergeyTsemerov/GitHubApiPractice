package ru.geekbrains.librariescoursepractice.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.librariescoursepractice.database.INetworkStatus
import ru.geekbrains.librariescoursepractice.database.IRepositoriesCache
import ru.geekbrains.librariescoursepractice.database.IUserCache
import ru.geekbrains.librariescoursepractice.model.*
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache
    ): IGitHubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun reposRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGitHubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}