package ru.geekbrains.librariescoursepractice.view

interface IUserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}

