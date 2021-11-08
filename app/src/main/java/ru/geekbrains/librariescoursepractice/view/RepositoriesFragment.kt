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
import ru.geekbrains.librariescoursepractice.databinding.FragmentRepositoriesBinding
import ru.geekbrains.librariescoursepractice.model.ApiHolder
import ru.geekbrains.librariescoursepractice.model.IGitHubUsersRepo
import ru.geekbrains.librariescoursepractice.model.RetrofitGithubUsersRepo
import ru.geekbrains.librariescoursepractice.presenter.RepositoriesPresenter
import ru.geekbrains.librariescoursepractice.presenter.RepositoriesView
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter

class RepositoriesFragment : MvpAppCompatFragment(), BackButtonListener, RepositoriesView {

    private var _binding: FragmentRepositoriesBinding? = null
    private val binding get() = _binding!!

    private val presenter: RepositoriesPresenter by moxyPresenter {
        RepositoriesPresenter(
            (arguments?.getString(REPOS)),
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
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REPOS = "repos"
        fun newInstance(repo: String?): RepositoriesFragment {
            val bundle = Bundle().apply { putString(REPOS, repo) }
            return RepositoriesFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        binding.run {
            this.recyclerViewRepositories.layoutManager = LinearLayoutManager(context)
            adapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)
            this.recyclerViewRepositories.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}