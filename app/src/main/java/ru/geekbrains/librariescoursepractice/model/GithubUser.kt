package ru.geekbrains.librariescoursepractice.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null
) : Parcelable