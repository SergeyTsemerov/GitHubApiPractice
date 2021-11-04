package ru.geekbrains.librariescoursepractice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.librariescoursepractice.databinding.ItemRepoBinding
import ru.geekbrains.librariescoursepractice.model.GithubRepository

class RepositoriesRVAdapter(private val repositories: List<GithubRepository>) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoriesRVAdapter.ViewHolder, position: Int) {
        holder.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root),
        IItemView {
        override var pos = -1

        fun bindView(view: ViewHolder) {
            binding.textViewRepo.text = repositories[view.pos].name
        }
    }
}