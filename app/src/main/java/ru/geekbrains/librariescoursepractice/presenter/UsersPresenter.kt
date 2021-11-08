package ru.geekbrains.librariescoursepractice.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.librariescoursepractice.model.IGitHubUsersRepo
import ru.geekbrains.librariescoursepractice.model.RetrofitGithubUsersRepo
import ru.geekbrains.librariescoursepractice.view.IScreens
import ru.geekbrains.librariescoursepractice.view.IUserItemView

class UsersPresenter(
    val uiScheduler: Scheduler,
    val usersRepo: IGitHubUsersRepo,
    val router: Router,
    val screen: IScreens
) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

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
            router.navigateTo(screen.login(user))
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
}