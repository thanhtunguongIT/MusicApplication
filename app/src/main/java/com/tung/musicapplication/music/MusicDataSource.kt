package com.tung.musicapplication.music

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tung.musicapplication.domain.EnAlbum
import java.lang.Exception

const val DEFAULT_NUMBER_OF_ITEM_PER_PAGE = 10

class MusicDataSource(
    private val currentAlbumList: List<EnAlbum>
) : PagingSource<Int, EnAlbum>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EnAlbum> {
        return try {
            val maxPageNumber = currentAlbumList.size / DEFAULT_NUMBER_OF_ITEM_PER_PAGE
            val pageNumber = params.key ?: 1
            val previousPageNumber = if (pageNumber > 1) (pageNumber - 1) else null
            val nextPageNumber = if (pageNumber < maxPageNumber) pageNumber + 1 else null

            val firstPageIndex = if (previousPageNumber == null) {
                0
            } else {
                previousPageNumber * DEFAULT_NUMBER_OF_ITEM_PER_PAGE
            }

            val lastPageIndex = if (nextPageNumber == null) {
                currentAlbumList.size - 1
            } else {
                pageNumber * DEFAULT_NUMBER_OF_ITEM_PER_PAGE - 1
            }

            val predicate: (Int, EnAlbum) -> Boolean = { index, album ->
                index > (firstPageIndex - 1) && index < (lastPageIndex + 1)
            }
            val pageData = currentAlbumList.filterIndexed(predicate)

            LoadResult.Page(
                data = pageData,
                prevKey = previousPageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EnAlbum>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}