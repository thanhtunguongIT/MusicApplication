package com.tung.musicapplication.interactors.album

import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.domain.EnAlbum
import com.tung.musicapplication.domain.EnPhoto
import com.tung.musicapplication.domain.EnSinger

class GetAlbumWithItsSingerAndPhotosUseCase {

    operator fun invoke(
        albumsData: RepositoryData<List<EnAlbum>>,
        photosData: RepositoryData<List<EnPhoto>>,
        singersData: RepositoryData<List<EnSinger>>
    ): List<EnAlbum> {
        val albums = mutableListOf<EnAlbum>()
        val photos = mutableListOf<EnPhoto>()
        val singers = mutableListOf<EnSinger>()

        if (albumsData is RepositoryData.Success) {
            albums.addAll(albumsData.data)
        }

        if (photosData is RepositoryData.Success) {
            photos.addAll(photosData.data)
        }

        if (singersData is RepositoryData.Success) {
            singers.addAll(singersData.data)
        }

        albums.apply {

            sortBy { album -> album.id }

            forEach { album ->
                singers.find { enSinger -> album.userId == enSinger.id }?.let { singer ->
                    album.singer = singer
                }
                photos.filter { photo -> photo.albumId == album.id }.let { photos ->
                    album.photos = photos
                }
            }
        }
        return albums
    }
}