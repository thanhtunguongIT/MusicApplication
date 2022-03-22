package com.tung.musicapplication.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tung.musicapplication.base.BaseViewModel
import com.tung.musicapplication.domain.EnAlbum
import com.tung.musicapplication.interactors.album.GetAlbumWithItsSingerAndPhotosUseCase
import com.tung.musicapplication.interactors.album.GetAlbumsUseCase
import com.tung.musicapplication.interactors.album.SearchAlbumBySingerName
import com.tung.musicapplication.interactors.photo.GetPhotosUseCase
import com.tung.musicapplication.interactors.singer.GetSingersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MusicViewState {

    object ShowList : MusicViewState()

    object EmptyList : MusicViewState()

    object EmptySearch : MusicViewState()

    object Loading : MusicViewState()
}

class MusicViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getSingersUseCase: GetSingersUseCase,
    private val getAlbumWithItsSingerAndPhotosUseCase: GetAlbumWithItsSingerAndPhotosUseCase,
    private val searchAlbumBySingerName: SearchAlbumBySingerName
) : BaseViewModel() {

    private val screenStateLiveData = MutableLiveData<MusicViewState>()
    val screenState: LiveData<MusicViewState> = screenStateLiveData

    private var processedAlbumList = mutableListOf<EnAlbum>()
    private var currentAlbumList = mutableListOf<EnAlbum>()

    val pagingData: Flow<PagingData<EnAlbum>> =
        Pager(PagingConfig(pageSize = DEFAULT_NUMBER_OF_ITEM_PER_PAGE)) {
            MusicDataSource(currentAlbumList)
        }.flow.cachedIn(viewModelScope)

    fun fetchAlbums() {
        screenStateLiveData.postValue(MusicViewState.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            val albumsAsync = async { getAlbumsUseCase.invoke() }
            val photosAsync = async { getPhotosUseCase.invoke() }
            val singersAsync = async { getSingersUseCase.invoke() }

            getAlbumWithItsSingerAndPhotosUseCase.invoke(
                albumsData = albumsAsync.await(),
                photosData = photosAsync.await(),
                singersData = singersAsync.await()
            ).let { albums ->
                processedAlbumList.clear()
                processedAlbumList.addAll(albums)

                currentAlbumList.clear()
                currentAlbumList.addAll(albums)

                screenStateLiveData.postValue(
                    if (albums.isEmpty()) MusicViewState.EmptyList else MusicViewState.ShowList
                )
            }
        }
    }

    fun searchAlbumBySinger(singerName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchAlbumBySingerName.invoke(
                albums = processedAlbumList,
                singerName = singerName
            ).let { albums ->
                currentAlbumList.clear()
                currentAlbumList.addAll(albums.ifEmpty { processedAlbumList })

                screenStateLiveData.postValue(
                    if (albums.isEmpty()) MusicViewState.EmptySearch else MusicViewState.ShowList
                )
            }
        }
    }
}