package com.tung.musicapplication.base

import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.domain.EnAlbum
import com.tung.musicapplication.domain.EnPhoto
import com.tung.musicapplication.domain.EnSinger

object TestConstants {

    const val ALBUMS_JSON_RESPONSE = "albums_response.json"

    const val ALBUMS_RESPONSE_SIZE = 100

    const val SINGERS_JSON_RESPONSE = "singers_response.json"

    const val SINGER_RESPONSE_SIZE = 10

    const val PHOTOS_JSON_RESPONSE = "photos_response.json"

    const val PHOTOS_RESPONSE_SIZE = 70

    const val singerId = 999
    const val singerName = "singerName"
    const val singerUsername = "singerUsername"
    const val albumId = 9999
    const val photoName = "photoName"

    val albumData = RepositoryData.Success(
        listOf(
            EnAlbum(
                id = albumId,
                userId = singerId,
                title = "testAlbum"
            )
        )
    )

    val singerData = RepositoryData.Success(
        listOf(
            EnSinger(
                id = singerId,
                name = singerName,
                userName = singerUsername
            )
        )
    )

    val photoData = RepositoryData.Success(
        listOf(
            EnPhoto(
                id = 1,
                albumId = albumId,
                title = photoName,
                url = "",
                thumbnailUrl = ""
            )
        )
    )
}