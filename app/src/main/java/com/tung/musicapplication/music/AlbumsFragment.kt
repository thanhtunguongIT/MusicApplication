package com.tung.musicapplication.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tung.musicapplication.R
import com.tung.musicapplication.base.BaseFragment
import com.tung.musicapplication.databinding.FragmentAlbumsBinding
import com.tung.musicapplication.di.AppComponent
import com.tung.musicapplication.domain.EnPhoto
import com.tung.musicapplication.photo.PhotosFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumsFragment : BaseFragment(), AlbumListClickListener {

    private lateinit var binding: FragmentAlbumsBinding

    private val viewModel: MusicViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[MusicViewModel::class.java]
    }

    private val albumsAdapter: AlbumsAdapter by lazy { AlbumsAdapter(this) }

    override fun doInjection(appComponent: AppComponent) {
        super.doInjection(appComponent)
        appComponent.inject(this@AlbumsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAlbumsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()

        viewModel.fetchAlbums()
    }

    private fun setupViews() = with((binding)) {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        rcvAlbums.apply {
            setHasFixedSize(true)
            adapter = albumsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        edSearchBar.doOnTextChanged { text, start, before, count ->
            val trimmedText = text?.trim().toString()
            viewModel.searchAlbumBySinger(trimmedText)
        }
    }

    private val albumsObserver = Observer<MusicViewState> { state ->
        when(state) {
            MusicViewState.Loading -> {
                binding.tvState.isVisible = true
                binding.tvState.text = getText(R.string.loading_text)
            }
            MusicViewState.ShowList -> {
                albumsAdapter.refresh()
                binding.tvState.isVisible = false
            }
            MusicViewState.EmptySearch -> {
                binding.tvState.isVisible = true
                binding.tvState.text = getText(R.string.search_result_empty)
            }
            MusicViewState.EmptyList -> {
                binding.tvState.isVisible = true
                binding.tvState.text = getText(R.string.empty_list)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.screenState.observe(viewLifecycleOwner, albumsObserver)

        lifecycleScope.launch {
            viewModel.pagingData.collectLatest {
                albumsAdapter.submitData(it)
            }
        }
    }

    override fun onItemClick(photos: List<EnPhoto>) {
        PhotosFragment(photos).show(childFragmentManager, PhotosFragment::class.java.toString())
    }
}