package com.example.amovies.remote

import com.example.amovies.remote.Responses.ConfigResponse
import com.example.amovies.remote.Responses.ImagesResponse

class ImageUrlCreator(private val api: MovieApiService) {

    private var configResponse: ImagesResponse? = null
    private var baseUrl: String? = null
    private val posterSize = "w300"
    private val backdropSize = "original"
    suspend fun getMoviePosterImageUrl(moviePosterPath: String): String? {
        loadConfiguration()

        return buildUrl(baseUrl, posterSize, moviePosterPath)
    }

    suspend fun getMovieBackdropImageUrl(moviePosterPath: String): String? {
        loadConfiguration()

        return buildUrl(baseUrl, backdropSize, moviePosterPath)
    }

    private fun buildUrl(url: String?,size: String, path: String?): String? {
        return if (url == null || path == null) {
            null
        } else {
            return "$url$size$path"
        }
    }

    private suspend fun loadConfiguration() {
        if (configResponse != null) return

        configResponse = api.loadConfiguration().images
        baseUrl = configResponse?.baseURL
    }
}
