package com.example.amovies.remote

import com.example.amovies.model.Actor
import com.example.amovies.model.Genre
import com.example.amovies.model.Movie
import com.example.amovies.model.MovieDetails
import kotlinx.serialization.SerialName

private const val ADULT_AGE = 16
private const val CHILD_AGE = 13
class RetrofitDataSource (
    private val api: MovieApiService,
    private val imageUrlCreator: ImageUrlCreator,
) : RemoteDataSource {
    override suspend fun loadMovies(): List<Movie> {
        val genres = api.loadGenres().genres

        return api.getMoviesList().results.map{ movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                imageUrl = imageUrlCreator.getMoviePosterImageUrl(movie.posterPath),
                rating = movie.voteAverage.toInt(),
                reviewCount = movie.voteCount,
                pgAge = movie.adult.toSpectatorAge(),
                isLiked = false,
                runningTime = 100,
                genres = genres
                    .filter { genreResp ->  movie.genreIds.contains(genreResp.id) }
                    .map { genre -> Genre(genre.id, genre.name) }
            )

        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        val details = api.loadMovieDetails(movieId)

        return MovieDetails(
            id = details.id,
            pgAge = details.adult.toSpectatorAge(),
            title = details.title,
            genres = details.genres.map { Genre(it.id, it.name) },
            reviewCount = details.revenue,
            isLiked = false,
            rating = details.popularity.toInt(),
            detailImageUrl = imageUrlCreator.getMovieBackdropImageUrl(details.backdropPath),
            storyLine = details.overview.orEmpty(),
            actors = arrayListOf(
                Actor(
                    id = 123,
                    name = "Niki",
                    imageUrl = "https://faroutmagazine.co.uk/static/uploads/1/2022/05/How-did-Dwayne-Johnson-become-the-highest-paid-Hollywood-actor.jpg"
                )
            )
/*            actors = api.loadMovieCredits(movieId).casts.map { actor ->
                Actor(
                    id = actor.id,
                    name = actor.name,
                    imageUrl = imageUrlAppender.getActorImageUrl(actor.profilePath)
                )
            }*/
        )
    }


    private fun Boolean.toSpectatorAge(): Int = if (this) ADULT_AGE else CHILD_AGE
}


/*@SerialName("adult") val adult : Boolean,
@SerialName("backdrop_path") val backdropPath : String?,
@SerialName("genres") val genres : List<com.example.amovies.remote.Responses.Genre>,
@SerialName("id") val id : Int,
@SerialName("overview") val overview : String?,
@SerialName("popularity") val popularity : Double,
@SerialName("revenue") val revenue : Int,
@SerialName("runtime") val runtime : Int?,
@SerialName("title") val title : String*/


/*
@SerialName("poster_path") val posterPath : String,
@SerialName("adult") val adult : Boolean,
@SerialName("genre_ids") val genreIds : List<Int>,
@SerialName("id") val id : Int,
@SerialName("title") val title : String,
@SerialName("vote_count") val voteCount : Int,
@SerialName("vote_average") val voteAverage : Double*/
