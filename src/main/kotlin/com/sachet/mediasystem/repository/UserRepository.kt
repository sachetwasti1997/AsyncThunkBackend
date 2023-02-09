package com.sachet.mediasystem.repository

import com.sachet.mediasystem.model.Photo
import com.sachet.mediasystem.model.User
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<User, Long> {
    @Query(
        "SELECT * FROM photo WHERE album_id=:albumId"
    )
    fun getAllPhotosInAlbum(albumId: Long): Flow<Photo>

}