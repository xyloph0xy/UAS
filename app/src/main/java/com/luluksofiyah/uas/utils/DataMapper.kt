package com.luluksofiyah.uas.utils

import com.luluksofiyah.uas.data.model.Movie
import com.luluksofiyah.uas.data.source.local.entity.MovieEntity
import com.luluksofiyah.uas.data.source.remote.response.MovieItem

object DataMapper {
    fun mapMovieResponseToEntity(input: List<MovieItem>): List<MovieEntity> =
        input.map {
            MovieEntity(
                id = it.id,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds,
                title = it.title
            )
        }

    fun mapMovieEntitiesToMovieModel(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds,
                title = it.title,
                isFavorite = it.isFavorite
            )
        }

    fun mapMovieEntityToMovieModel(input: MovieEntity): Movie =
        input.let {
            Movie(
                id = it.id,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds,
                title = it.title,
                isFavorite = it.isFavorite
            )
        }

    fun mapGenreIdToGenre(id: List<Int?>): List<String?> =
        id.map { genre ->
            when (genre) {
                28 -> "Action"
                12 -> "Adventure"
                16 -> "Animation"
                35 -> "Comedy"
                80 -> "Crime"
                99 -> "Documentary"
                18 -> "Drama"
                10751 -> "Family"
                14 -> "Fantasy"
                36 -> "History"
                27 -> "Horror"
                10402 -> "Music"
                9648 -> "Mystery"
                10749 -> "Romance"
                878 -> "Science Fiction"
                10770 -> "TV Movie"
                53 -> "Thriller"
                10752 -> "War"
                37 -> "Western"
                else -> null
            }
        }
}