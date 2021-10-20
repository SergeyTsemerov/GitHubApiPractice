package ru.geekbrains.librariescoursepractice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.librariescoursepractice.*
import ru.geekbrains.librariescoursepractice.databinding.FragmentLoginBinding
import ru.geekbrains.librariescoursepractice.model.GithubUsersRepo
import ru.geekbrains.librariescoursepractice.presenter.GithubUser
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter
import ru.geekbrains.librariescoursepractice.presenter.UsersView

class LoginFragment : MvpAppCompatFragment(), BackButtonListener, UsersView {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens())
    }

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

    companion object {
        private const val LOGIN = "login"
        fun newInstance(user: GithubUser): MvpAppCompatFragment {
            val bundle = Bundle()
            bundle.putString(LOGIN, user.login)
            return LoginFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        arguments?.let {
            binding.loginTextView.text = it.getString(LOGIN)
        }
    }

    override fun updateList() {
    }
}