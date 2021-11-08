package ru.geekbrains.librariescoursepractice.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.librariescoursepractice.databinding.ItemRepoBinding
import ru.geekbrains.librariescoursepractice.presenter.IRepoListPresenter

class RepositoriesRVAdapter(private val presenter: IRepoListPresenter) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(this)
        }
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: RepositoriesRVAdapter.ViewHolder, position: Int) {
        return presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root),
        IRepoItemView {

        override var pos = -1

        override fun setRepoName(name: String) {
            binding.textViewRepo.text = name
        }
    }
}