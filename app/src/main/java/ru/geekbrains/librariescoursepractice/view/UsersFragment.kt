package ru.geekbrains.librariescoursepractice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.librariescoursepractice.*
import ru.geekbrains.librariescoursepractice.databinding.FragmentUsersBinding
import ru.geekbrains.librariescoursepractice.model.ApiHolder
import ru.geekbrains.librariescoursepractice.model.RetrofitGithubUsersRepo
import ru.geekbrains.librariescoursepractice.presenter.UsersPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router,
            AndroidScreens()
        )
    }
    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.run {
            this.recyclerViewUsers.layoutManager = LinearLayoutManager(context)
            adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
            this.recyclerViewUsers.adapter = adapter
        }

        binding.topImage.run {
            Glide.with(this)
                .load("https://www.influxdata.com/wp-content/uploads/GitHub-logo.jpg")
                .placeholder(R.drawable.image_error)
                .into(this)
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}