package com.sachet.mediasystem.controller

import com.sachet.mediasystem.model.PathRes
import com.sachet.mediasystem.model.Photo
import com.sachet.mediasystem.service.PhotoService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.io.File
import java.io.IOException
import java.nio.file.Paths

@RestController
@RequestMapping("/api/v1/photo")
class PhotoController(
    val photoService: PhotoService
) {
    @PostMapping(
        "/{albumId}",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    suspend fun createPhoto(
        @PathVariable("albumId") albumId: Long,
        @RequestPart("fileToUpload") filePart: Mono<FilePart>): ResponseEntity<Photo> {
        return try {
            val file = filePart.awaitSingle()
            ResponseEntity(photoService.createPhoto(albumId, file), HttpStatus.ACCEPTED)
        }catch (ex: IOException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/album/{albumId}")
    suspend fun deletePhotosInAlbum(@PathVariable albumId: Long) {
        photoService.deletePhotosInAlbum(albumId)
    }

    @PostMapping(produces = [MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE])
    suspend fun getPhoto(@RequestBody path: PathRes) : File {
        val fl = Paths.get(path.path)
        return fl.toFile()
    }

    @GetMapping("/{albumId}")
    suspend fun getAllPhotoInAlbum(@PathVariable albumId: Long)
        = photoService.getAllPhotoInAlbum(albumId)

    @DeleteMapping("/{id}")
    suspend fun deleteById(@PathVariable id: Long) = photoService.deletePhotoById(id)
}