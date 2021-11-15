package ru.geekbrains.librariescoursepractice.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.librariescoursepractice.di.IUsersScopeContainer
import ru.geekbrains.librariescoursepractice.model.GithubUser
import ru.geekbrains.librariescoursepractice.model.IGitHubUsersRepo
import ru.geekbrains.librariescoursepractice.view.IScreens
import ru.geekbrains.librariescoursepractice.view.IUserItemView
import ru.geekbrains.librariescoursepractice.view.UsersView
import javax.inject.Inject

class UsersPresenter(private val uiScheduler: Scheduler) : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGitHubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    @Inject
    lateinit var usersScopeContainer: IUsersScopeContainer

    class UsersListPresenter : IUserListPresenter {

        val users = (1..20).map { GithubUser("login $it") }.toMutableList()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screen.repositories(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        usersScopeContainer.releaseUsersScope()
        super.onDestroy()
    }
}