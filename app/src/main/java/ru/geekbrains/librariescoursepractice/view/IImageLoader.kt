package ru.geekbrains.librariescoursepractice.view

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}