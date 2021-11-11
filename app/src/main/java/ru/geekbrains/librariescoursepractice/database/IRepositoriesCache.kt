package ru.geekbrains.librariescoursepractice.database

import ru.geekbrains.librariescoursepractice.model.GithubRepository
import ru.geekbrains.librariescoursepractice.model.GithubUser

interface IRepositoriesCache {

    fun saveReposToDB(repositories: List<GithubRepository>, users: GithubUser?)
    fun getReposFromDB(users: GithubUser?): List<GithubRepository>
}