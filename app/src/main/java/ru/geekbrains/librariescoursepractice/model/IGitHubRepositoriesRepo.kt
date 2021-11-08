package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single

interface IGitHubRepositoriesRepo {
    fun getRepositories(login: String?): Single<List<GithubRepository>>
}