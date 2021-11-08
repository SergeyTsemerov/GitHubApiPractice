package ru.geekbrains.librariescoursepractice.view

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.librariescoursepractice.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun repositories(login: String?): Screen
}

