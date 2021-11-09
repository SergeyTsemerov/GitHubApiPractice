package ru.geekbrains.librariescoursepractice.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET
    fun loadRepositories(@Url url: String?): Single<List<GithubRepository>>
}