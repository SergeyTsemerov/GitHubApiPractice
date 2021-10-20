package ru.geekbrains.librariescoursepractice.view

import ru.geekbrains.librariescoursepractice.view.IItemView

interface IUserItemView : IItemView {
    fun setLogin(text: String)
}

