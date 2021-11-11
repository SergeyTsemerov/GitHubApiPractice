package ru.geekbrains.librariescoursepractice.database

import ru.geekbrains.librariescoursepractice.model.GithubUser

interface IUserCache {

    fun saveUsersToDataBase(users: List<GithubUser>)
    fun getUsersFromDataBase(): List<GithubUser>
}