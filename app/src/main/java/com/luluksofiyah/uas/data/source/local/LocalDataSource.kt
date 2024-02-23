package com.luluksofiyah.uas.data.source.local

import com.luluksofiyah.uas.data.source.local.entity.MovieEntity
import com.luluksofiyah.uas.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovies() = movieDao.getMovies()

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    fun getMovieById(movieId: Int) = movieDao.getMovieById(movieId)

    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean) =
        movieDao.updateMovieById(movieId, isFavorite)
}