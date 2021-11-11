package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.librariescoursepractice.database.*

class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    val dataBase: IRepositoriesCache
) : IGitHubRepositoriesRepo {

    override fun getRepositories(user: GithubUser?): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user?.reposUrl?.let { url ->
                    api.loadRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                dataBase.saveReposToDB(repositories, user)
                                repositories
                            }
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos"))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    dataBase.getReposFromDB(user)
                }.subscribeOn(Schedulers.io())
            }
        }
}