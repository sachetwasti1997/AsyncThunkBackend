package com.sachet.mediasystem.repository

import com.sachet.mediasystem.model.Album
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AlbumRepository: CoroutineCrudRepository<Album, Long> {

    suspend fun getAllByUserId(userId: Long): List<Album>

    suspend fun deleteAllByUserId(userId: Long)

}