package com.sachet.mediasystem.service

import com.sachet.mediasystem.model.Album
import com.sachet.mediasystem.repository.AlbumRepository
import com.sachet.mediasystem.repository.PhotoRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class AlbumService(
    val albumRepository: AlbumRepository,
    val photoRepository: PhotoRepository
) {

    suspend fun getAlbumById(id: Long): Album? {
        return albumRepository.findById(id);
    }

    suspend fun createAlbum(album: Album): Album {
        return albumRepository.save(album)
    }

    suspend fun getAlbumOfUser(userId: Long): List<Album> {
        return albumRepository.getAllByUserId(userId).toList()
    }

    suspend fun deleteAlbumsByUserId(userId: Long) {
        val albums = getAlbumOfUser(userId)
        for (i in albums) i.id?.let { photoRepository.deleteAllByAlbumId(it) }
        albumRepository.deleteAllByUserId(userId)
    }

    suspend fun deleteAlbum(albumId: Long) {
        photoRepository.deleteAllByAlbumId(albumId)
        albumRepository.deleteById(albumId)
    }

}