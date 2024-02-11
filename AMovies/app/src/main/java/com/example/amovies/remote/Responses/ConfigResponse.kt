package com.example.amovies.remote.Responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigResponse (
    val images: Images
)

@Serializable
data class Images (
    @SerialName("secure_base_url") val baseURL: String,
)
