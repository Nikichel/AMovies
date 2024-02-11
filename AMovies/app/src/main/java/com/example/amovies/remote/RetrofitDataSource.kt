package com.example.amovies.remote

import com.example.amovies.model.Movie

private const val ADULT_AGE = 16
private const val CHILD_AGE = 13
class RetrofitDataSource (
    private val api: MovieApiService,
    private val imageUrlCreator: ImageUrlCreator,
) : RemoteDataSource {
    override suspend fun loadMovies(): List<Movie> {
        return api.getMoviesList().results.map{ movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                imageUrl = imageUrlCreator.getMoviePosterImageUrl(movie.posterPath),
                rating = movie.voteAverage.toInt(),
                reviewCount = movie.voteCount,
                pgAge = movie.adult.toSpectatorAge(),
                isLiked = false,
                runningTime = 100
            )

        }
    }

    private fun Boolean.toSpectatorAge(): Int = if (this) ADULT_AGE else CHILD_AGE
}

/*
@SerialName("poster_path") val posterPath : String,
@SerialName("adult") val adult : Boolean,
@SerialName("genre_ids") val genreIds : List<Int>,
@SerialName("id") val id : Int,
@SerialName("title") val title : String,
@SerialName("vote_count") val voteCount : Int,
@SerialName("vote_average") val voteAverage : Double*/
