package com.example.amovies.remote

import com.example.amovies.remote.Responses.ConfigResponse
import com.example.amovies.remote.Responses.ImagesResponse

class ImageUrlCreator(private val api: MovieApiService) {

    private var configResponse: ImagesResponse? = null
    private var baseUrl: String? = null
    private val posterSize = "w300"
    suspend fun getMoviePosterImageUrl(moviePosterPath: String): String? {
        loadConfiguration()

        return buildUrl(baseUrl, moviePosterPath)
    }

    private fun buildUrl(url: String?, path: String?): String? {
        return if (url == null || path == null) {
            null
        } else {
            return "$url$posterSize$path"
        }
    }

    private suspend fun loadConfiguration() {
        if (configResponse != null) return

        configResponse = api.loadConfiguration().images
        baseUrl = configResponse?.baseURL
    }
}
