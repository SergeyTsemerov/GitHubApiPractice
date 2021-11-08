package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.librariescoursepractice.database.DataBase
import ru.geekbrains.librariescoursepractice.database.INetworkStatus
import ru.geekbrains.librariescoursepractice.database.RoomGithubRepository

class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    val networkStatus: INetworkStatus,
    val dataBase: DataBase
) : IGitHubRepositoriesRepo {

    override fun getRepositories(login: String?): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.loadRepositories(login)
                    .flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = GithubUser().login?.let {
                                dataBase.userDao.findByLogin(it)
                            } ?: throw  RuntimeException("No such user in cache")
                            val roomRepos = repositories.map {
                                RoomGithubRepository(
                                    it.id ?: "",
                                    it.name ?: "",
                                    it.forks ?: 0,
                                    roomUser.id
                                )
                            }
                            dataBase.repositoryDao.insert(roomRepos)
                            repositories
                        }
                    } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos"))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    val roomUser = GithubUser().login?.let { dataBase.userDao.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    dataBase.repositoryDao.findForUser(roomUser.id).map {
                        GithubRepository(it.id, it.name, it.forksCount)
                    }
                }.subscribeOn(Schedulers.io())
            }
        }
}