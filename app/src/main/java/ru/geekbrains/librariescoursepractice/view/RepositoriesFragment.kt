package ru.geekbrains.librariescoursepractice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.librariescoursepractice.App
import ru.geekbrains.librariescoursepractice.databinding.FragmentLoginBinding
import ru.geekbrains.librariescoursepractice.model.ApiHolder
import ru.geekbrains.librariescoursepractice.model.IGitHubUsersRepo
import ru.geekbrains.librariescoursepractice.model.RetrofitGithubUsersRepo
import ru.geekbrains.librariescoursepractice.presenter.GithubUser
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter

class RepositoriesFragment : MvpAppCompatFragment(), BackButtonListener, ReposView {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router,
            AndroidScreens()
        )
    }

    private var adapter: RepositoriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setLogin(login: String) {
        val usersRepo: IGitHubUsersRepo = RetrofitGithubUsersRepo(ApiHolder.api)
        usersRepo.getRepositories(login).observeOn(AndroidSchedulers.mainThread())
            .subscribe { repos ->
                binding.recyclerViewUsers.layoutManager =
                    LinearLayoutManager(context)
                adapter = RepositoriesRVAdapter(repos)
                binding.recyclerViewUsers.adapter = adapter
            }
    }

    companion object {
        private const val LOGIN = "login"
        fun newInstance(user: GithubUser): MvpAppCompatFragment {
            val bundle = Bundle()
            bundle.putString(LOGIN, user.login)
            return RepositoriesFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun backPressed() = presenter.backPressed()
}