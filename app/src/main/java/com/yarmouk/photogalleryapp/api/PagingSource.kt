package com.yarmouk.photogalleryapp.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yarmouk.photogalleryapp.models.UnSplashImage
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

//PagingSource for our images which will query more images when
//you reach the end of the list
class PagingSource(
    private val unsplashApi: UnSplashApi,
    private val query: String
) : PagingSource<Int, UnSplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnSplashImage> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            //get list of images from the api passing the keyword, page number and number of images per_page
            val response = unsplashApi.searchForImages(query, position, params.loadSize)
            //list of images returned from the api
            val images = response.results

            //return the results
            LoadResult.Page(
                data = images,
                //here we check if we are in page 1 then previous key will be null otherwise subtract 1 from position
                //by this we will get the previous page we query for
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                //here we check if list of images is empty then next key to query will be null and stop paging
                //otherwise add 1 to position
                //by this we will get the next page we want to query for
                nextKey = if (images.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            //return IOException
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            //return HttpException (errors in the internet or api)
            LoadResult.Error(exception)
        }
    }

    //get the most recently accessed index.
    override fun getRefreshKey(state: PagingState<Int, UnSplashImage>): Int? {
        return state.anchorPosition
    }
}