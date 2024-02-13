package com.example.amovies.remote.Responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("adult") val adult : Boolean,
    @SerialName("backdrop_path") val backdropPath : String,
    @SerialName("genres") val genres : List<GenreResponse>,
    @SerialName("id") val id : Int,
    @SerialName("overview") val overview : String?,
    @SerialName("vote_average") val voteAverage : Double,
    @SerialName("vote_count") val voteCount : Int,
    @SerialName("runtime") val runtime : Int?,
    @SerialName("title") val title : String
)
