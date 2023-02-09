package com.sachet.mediasystem.repository

import com.sachet.mediasystem.model.Album
import com.sachet.mediasystem.model.Photo
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import reactor.core.publisher.Flux

interface PhotoRepository : CoroutineCrudRepository<Photo, Long> {

    suspend fun deleteAllByAlbumId(albumId: Long)

    suspend fun getAllByAlbumId(albumId: Long): Flow<Photo>
}