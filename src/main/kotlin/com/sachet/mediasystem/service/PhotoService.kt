package com.sachet.mediasystem.service

import com.sachet.mediasystem.model.Photo
import com.sachet.mediasystem.repository.PhotoRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import java.nio.file.Paths
import java.util.Date

@Service
class PhotoService(
    val photoRepository: PhotoRepository
) {

    suspend fun createPhoto(albumId: Long, filePart: FilePart): Photo {
        val basePath = Paths.get("./src/main/resources/upload")
        val path = Date().time.toString()+"-file"+filePart.filename()
        filePart.transferTo(basePath.resolve(path)).awaitSingleOrNull()
        val photo = Photo(
            name = path,
            path = path,
            albumId = albumId
        )
        return photoRepository.save(photo)
    }

    suspend fun deletePhotoById(id: Long): String {
        val photo = photoRepository.findById(id)
        photo?.let {
            photoRepository.deleteById(id)
            return "Deleted Successfully"
        }
        return "Photo with Id Not Found"
    }

    suspend fun deletePhotosInAlbum(albumId: Long) {
        photoRepository.deleteAllByAlbumId(albumId)
    }

    suspend fun getAllPhotoInAlbum(albumId: Long): List<Photo> {
        return photoRepository.getAllByAlbumId(albumId).toList()
    }
}