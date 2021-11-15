package ru.geekbrains.librariescoursepractice.di

import dagger.Subcomponent
import ru.geekbrains.librariescoursepractice.presenter.RepositoriesPresenter
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repositoriesPresenter: RepositoriesPresenter)
}

