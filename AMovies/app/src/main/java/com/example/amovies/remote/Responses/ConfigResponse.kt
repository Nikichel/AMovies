package com.example.amovies.remote.Responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigResponse (
    val images: ImagesResponse
)

@Serializable
data class ImagesResponse(
    @SerialName("secure_base_url") val baseURL: String,
)
