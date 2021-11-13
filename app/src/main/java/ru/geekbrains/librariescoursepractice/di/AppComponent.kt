package ru.geekbrains.librariescoursepractice.di

import dagger.Component
import ru.geekbrains.librariescoursepractice.presenter.MainPresenter
import ru.geekbrains.librariescoursepractice.presenter.RepositoriesPresenter
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter
import ru.geekbrains.librariescoursepractice.view.MainActivity
import ru.geekbrains.librariescoursepractice.view.RepositoriesFragment
import ru.geekbrains.librariescoursepractice.view.UsersFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repositoriesPresenter: RepositoriesPresenter)
}