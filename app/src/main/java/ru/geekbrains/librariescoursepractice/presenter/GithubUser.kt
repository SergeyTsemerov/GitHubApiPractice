package ru.geekbrains.librariescoursepractice.presenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubUser(val login: String) : Parcelable