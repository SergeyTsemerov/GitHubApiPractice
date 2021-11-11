package ru.geekbrains.librariescoursepractice.database

import ru.geekbrains.librariescoursepractice.model.GithubRepository
import ru.geekbrains.librariescoursepractice.model.GithubUser

class RoomRepositoriesCache(private val database: DataBase) : IRepositoriesCache {

    override fun saveReposToDB(repositories: List<GithubRepository>, users: GithubUser?) {
        val roomUser = users?.login?.let {
            database.userDao.findByLogin(it)
        } ?: throw  RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(
                it.id,
                it.name ?: "",
                it.forks ?: 0,
                roomUser.id
            )
        }
        database.repositoryDao.insert(roomRepos)
    }

    override fun getReposFromDB(users: GithubUser?): List<GithubRepository> {
        val roomUser = users?.login?.let { database.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        return database.repositoryDao.findForUser(roomUser.id).map {
            GithubRepository(it.id, it.name, it.forksCount)
        }
    }
}