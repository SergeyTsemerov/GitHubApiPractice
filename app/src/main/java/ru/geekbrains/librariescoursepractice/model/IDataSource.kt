package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<GithubUser>

    @GET("users/{login}/repos")
    fun loadRepositories(@Path("login") login: String?): Single<List<GithubRepository>>
}