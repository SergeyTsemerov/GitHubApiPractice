package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: IDataSource) : IGitHubUsersRepo {

    override fun getUsers() = api.loadUsers().subscribeOn(Schedulers.io())
    override fun getRepositories(login: String?): Single<List<GithubRepository>> {
        return api.loadRepositories(login).subscribeOn(Schedulers.io())
    }
}

