package ru.geekbrains.librariescoursepractice.database

import ru.geekbrains.librariescoursepractice.model.GithubUser

class RoomUserCache(private val database: DataBase) : IUserCache {

    override fun saveUsersToDataBase(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        database.userDao.insert(roomUsers)
    }

    override fun getUsersFromDataBase(): List<GithubUser> {
        return database.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.login,
                roomUser.id,
                roomUser.avatarUrl,
                roomUser.reposUrl
            )
        }
    }
}