package com.luluksofiyah.uas.data.repository

import com.luluksofiyah.uas.data.Resource
import com.luluksofiyah.uas.data.model.Movie
import com.luluksofiyah.uas.data.networkBoundResource
import com.luluksofiyah.uas.data.source.local.LocalDataSource
import com.luluksofiyah.uas.data.source.remote.RemoteDataSource
import com.luluksofiyah.uas.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {
    fun getPopularMovies(): Flow<Resource<List<Movie>>> = networkBoundResource(
        query = {
            local.getMovies().map {
                DataMapper.mapMovieEntitiesToMovieModel(it)
            }
        },
        fetch = {
            remote.getPopularMovies()
        },
        saveFetchResult = {
            val entity = DataMapper.mapMovieResponseToEntity(it)
            local.insertMovies(entity)
        },
        shouldFetch = {
            it.isNullOrEmpty()
        }
    )

    fun getFavoriteMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val movieEntity = local.getFavoriteMovies().first()
            val movie = DataMapper.mapMovieEntitiesToMovieModel(movieEntity)
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }

    fun getMovieById(movieId: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        try {
            val movieEntity = local.getMovieById(movieId).first()
            val movie = DataMapper.mapMovieEntityToMovieModel(movieEntity)
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean) =
        local.updateMovieById(movieId, isFavorite)
}