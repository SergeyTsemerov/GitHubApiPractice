package ru.geekbrains.librariescoursepractice.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubRepository(
    @Expose val name: String? = null,
    @Expose val reposUrl: String? = null,
    @Expose val forks: Int? = null
) : Parcelable