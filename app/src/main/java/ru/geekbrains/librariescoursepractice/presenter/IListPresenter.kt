package ru.geekbrains.librariescoursepractice.presenter

import ru.geekbrains.librariescoursepractice.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}