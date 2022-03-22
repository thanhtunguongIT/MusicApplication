package com.tung.musicapplication.domain

data class EnAlbum(
    val id: Int,
    val userId: Int,
    val title: String,
    var singer: EnSinger? = null,
    var photos: List<EnPhoto>? = null
)
