package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.librariescoursepractice.database.*

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val dataBase: IUserCache
) : IGitHubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.loadUsers()
                    .flatMap { users ->
                        Single.fromCallable {
                            dataBase.saveUsersToDataBase(users)
                            users
                        }
                    }
            } else {
                Single.fromCallable {
                    dataBase.getUsersFromDataBase()
                }
            }
        }.subscribeOn(Schedulers.io())
}
