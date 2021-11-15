package ru.geekbrains.librariescoursepractice.di

import dagger.Component
import ru.geekbrains.librariescoursepractice.presenter.MainPresenter
import ru.geekbrains.librariescoursepractice.presenter.RepositoriesPresenter
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter
import ru.geekbrains.librariescoursepractice.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ApiModule::class
    ]
)
interface AppComponent {

    fun usersSubcomponent(): UsersSubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}