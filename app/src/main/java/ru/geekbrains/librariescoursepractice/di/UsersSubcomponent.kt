package ru.geekbrains.librariescoursepractice.di

import dagger.Subcomponent
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter

@UsersScope
@Subcomponent(
    modules = [
        UsersModule::class
    ]
)
interface UsersSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
}