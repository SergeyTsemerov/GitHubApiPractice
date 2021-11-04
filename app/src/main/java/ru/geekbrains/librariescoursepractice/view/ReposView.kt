package ru.geekbrains.librariescoursepractice.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ReposView : MvpView {
    fun setLogin(login: String)
}