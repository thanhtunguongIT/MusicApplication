package com.tung.musicapplication.usecase

import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.domain.EnAlbum
import com.tung.musicapplication.domain.EnSinger
import com.tung.musicapplication.interactors.album.SearchAlbumBySingerName
import org.junit.Test

class SearchAlbumBySingerNameTest {

    @Test
    fun testSearchAlbumBySingerNameResultFound() {
        val albums = mutableListOf<EnAlbum>()

        val name1 = TestConstants.singerName
        val name2 = "Yeah"

        val item1 = TestConstants.albumData.data[0].copy(
            singer = EnSinger(
                id = 0,
                name = name1
            )
        )

        val item2 = TestConstants.albumData.data[0].copy(
            singer = EnSinger(
                id = 1,
                userName = name2
            )
        )

        albums.add(item1)
        albums.add(item2)

        val underTest = SearchAlbumBySingerName()

        val resultCanBeFoundByName = underTest.invoke(albums, name1)
        assert(albums.size == 2)
        assert(resultCanBeFoundByName.size == 1)
        assert(resultCanBeFoundByName[0].singer?.name == name1)
        assert(resultCanBeFoundByName[0].singer?.id == item1.singer?.id)

        val resultCanBeFoundByUserName = underTest.invoke(albums, name2)
        assert(albums.size == 2)
        assert(resultCanBeFoundByUserName.size == 1)
        assert(resultCanBeFoundByUserName[0].singer?.userName == name2)
        assert(resultCanBeFoundByUserName[0].singer?.id == item2.singer?.id)

        val nameThatCannotBeFound = "$name1$name2"
        val resultCanNotBeFoundByUserName = underTest.invoke(albums, nameThatCannotBeFound)
        assert(albums.size == 2)
        assert(resultCanNotBeFoundByUserName.isEmpty())
    }
}