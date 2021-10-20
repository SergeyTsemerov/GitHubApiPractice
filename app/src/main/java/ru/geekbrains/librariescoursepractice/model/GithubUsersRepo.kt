package ru.geekbrains.librariescoursepractice.model

import ru.geekbrains.librariescoursepractice.presenter.GithubUser

class GithubUsersRepo {

    val repositories = (0..100).map { GithubUser("login $it") }

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}