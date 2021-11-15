package ru.geekbrains.librariescoursepractice.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.librariescoursepractice.view.IScreens
import ru.geekbrains.librariescoursepractice.view.MainView
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screen: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun backClicked() {
        router.exit()
    }
}

