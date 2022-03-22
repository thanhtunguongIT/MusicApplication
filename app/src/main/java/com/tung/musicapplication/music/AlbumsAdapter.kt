package com.tung.musicapplication.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tung.musicapplication.databinding.ItemAlbumBinding
import com.tung.musicapplication.domain.EnAlbum
import com.tung.musicapplication.domain.EnPhoto
import com.tung.musicapplication.extension.loadUrl

interface AlbumListClickListener {

    fun onItemClick(photos: List<EnPhoto>)
}

class AlbumsAdapter(
    private var onItemClickListener: AlbumListClickListener? = null
) : PagingDataAdapter<EnAlbum, AlbumViewHolder>(AlbumComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener {
                getItem(position)?.let { album ->
                    onItemClickListener?.onItemClick(album.photos ?: arrayListOf())
                }
            }
        }
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        getItem(position)?.let { item -> holder.bind(item) }
    }

    object AlbumComparator : DiffUtil.ItemCallback<EnAlbum>() {
        override fun areItemsTheSame(oldItem: EnAlbum, newItem: EnAlbum): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EnAlbum, newItem: EnAlbum): Boolean {
            return oldItem == newItem
        }
    }
}

class AlbumViewHolder(private val binding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(album: EnAlbum) = with(binding) {
        tvAlbumTitle.text = album.title
        tvAlbumSinger.text = album.singer?.name

        if ((album.photos?.size ?: 0) > 0) {
            album.photos?.get(0)?.let {
                ivAlbumThumbnail.loadUrl(it.thumbnailUrl)
            }
        }
    }
}