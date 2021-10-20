package ru.geekbrains.librariescoursepractice.view

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.librariescoursepractice.presenter.GithubUser

interface IScreens {
    fun users(): Screen
    fun login(user: GithubUser): Screen
}

