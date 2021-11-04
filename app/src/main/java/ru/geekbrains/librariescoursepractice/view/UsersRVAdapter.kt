package ru.geekbrains.librariescoursepractice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.librariescoursepractice.presenter.IUserListPresenter
import ru.geekbrains.librariescoursepractice.databinding.ItemUserBinding

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), IUserItemView {

        override var pos = -1

        override fun setLogin(text: String) = with(binding) {
            textViewLogin.text = text
        }

        override fun loadAvatar(url: String) = with(binding) {
            imageLoader.loadInto(url, binding.imageViewAvatar)
        }
    }
}
