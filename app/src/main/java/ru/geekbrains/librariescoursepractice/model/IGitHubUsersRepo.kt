package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.librariescoursepractice.presenter.GithubUser

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getRepositories(login: String): Single<List<GithubRepository>>
}