package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Observable
import ru.geekbrains.librariescoursepractice.presenter.GithubUser

class GithubUsersRepo {

    private val repositories = (0..10).map { GithubUser("login $it") }

    fun getUsers(): Observable<List<GithubUser>> {
        return Observable.just(repositories)
    }
}