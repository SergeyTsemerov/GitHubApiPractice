package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.librariescoursepractice.database.DataBase
import ru.geekbrains.librariescoursepractice.database.INetworkStatus
import ru.geekbrains.librariescoursepractice.database.RoomGithubUser

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val dataBase: DataBase
) : IGitHubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.loadUsers()
                    .flatMap { users ->
                        Single.fromCallable {
                            val roomUsers = users.map { user ->
                                RoomGithubUser(
                                    user.id ?: "",
                                    user.login ?: "",
                                    user.avatarUrl ?: "",
                                    user.reposUrl ?: ""
                                )
                            }
                            dataBase.userDao.insert(roomUsers)
                            users
                        }
                    }
            } else {
                Single.fromCallable {
                    dataBase.userDao.getAll().map { roomUser ->
                        GithubUser(
                            roomUser.login,
                            roomUser.id,
                            roomUser.avatarUrl,
                            roomUser.reposUrl
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}
