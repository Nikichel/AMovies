package com.example.amovies.remote.Responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class  CastResponse(
    @SerialName("id") val id: Int,
    @SerialName("cast") val casts: List<Cast>
)

@Serializable
data class Cast (
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val profilePath: String?
)
