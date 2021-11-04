package ru.geekbrains.librariescoursepractice.view

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.librariescoursepractice.presenter.GithubUser

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun login(user: GithubUser): Screen {
        return FragmentScreen { RepositoriesFragment.newInstance(user) }
    }
}