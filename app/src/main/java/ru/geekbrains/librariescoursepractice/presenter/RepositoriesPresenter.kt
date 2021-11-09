package ru.geekbrains.librariescoursepractice.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.geekbrains.librariescoursepractice.model.GithubRepository
import ru.geekbrains.librariescoursepractice.model.GithubUser
import ru.geekbrains.librariescoursepractice.model.IGitHubRepositoriesRepo
import ru.geekbrains.librariescoursepractice.view.IRepoItemView
import ru.geekbrains.librariescoursepractice.view.IScreens
import ru.geekbrains.librariescoursepractice.view.RepositoriesView

class RepositoriesPresenter(
    private val user: GithubUser?,
    private val repository: IGitHubRepositoriesRepo,
    private val router: Router,
    private val screen: IScreens
) : MvpPresenter<RepositoriesView>() {
    class RepositoriesListPresenter : IRepoListPresenter {

        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((IRepoItemView) -> Unit)? = null
        override fun getCount(): Int = repositories.size
        override fun bindView(view: IRepoItemView) {
            val repo = repositories[view.pos]
            repo.name?.let { view.setRepoName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        repository.getRepositories(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.navigateTo(screen.users())
        return true
    }
}
