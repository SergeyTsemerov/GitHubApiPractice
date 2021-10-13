package ru.geekbrains.librariescoursepractice

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun firstCounterClick() {
        val nextValue = model.next(0)
        view.setFirstButtonText(nextValue.toString())
    }

    fun secondCounterClick() {
        val nextValue = model.next(1)
        view.setSecondButtonText(nextValue.toString())
    }

    fun thirdCounterClick() {
        val nextValue = model.next(2)
        view.setThirdButtonText(nextValue.toString())
    }
}
