package com.tung.musicapplication.interactors.album

import com.tung.musicapplication.domain.EnAlbum

class SearchAlbumBySingerName {

    operator fun invoke(albums: List<EnAlbum>, singerName: String): List<EnAlbum> {
        val singerNameLowerCase = singerName.lowercase()
        return if (singerName.isNotBlank() && albums.isEmpty().not()) {
            val result = albums.filter { album ->
                predicateSearch(album, singerNameLowerCase)
            }
            result
        } else {
            albums
        }
    }

    private fun predicateSearch(album: EnAlbum, searchNameLowerCase: String): Boolean {
        album.singer?.let { singer ->
            val name = singer.name?.lowercase()
            val username = singer.userName?.lowercase()

            return name.equals(searchNameLowerCase)
                    || name?.contains(searchNameLowerCase) == true
                    || username.equals(searchNameLowerCase)
                    || username?.contains(searchNameLowerCase) == true
        }
        return false
    }
}