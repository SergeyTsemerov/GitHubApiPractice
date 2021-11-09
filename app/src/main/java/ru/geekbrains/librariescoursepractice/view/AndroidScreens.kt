package ru.geekbrains.librariescoursepractice.view

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.librariescoursepractice.model.GithubUser

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun repositories(user: GithubUser?): Screen {
        return FragmentScreen { RepositoriesFragment.newInstance(user) }
    }
}