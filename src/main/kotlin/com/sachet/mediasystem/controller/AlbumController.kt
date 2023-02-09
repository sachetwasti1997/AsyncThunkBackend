package com.sachet.mediasystem.controller

import com.sachet.mediasystem.model.Album
import com.sachet.mediasystem.service.AlbumService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/album")
class AlbumController(
    val albumService: AlbumService
) {

    @GetMapping("/user/{id}")
    suspend fun getAllAlbumsOfUser(@PathVariable id: Long) = albumService.getAlbumOfUser(id)

    @PostMapping
    suspend fun createAlbum(@RequestBody album: Album) = albumService.createAlbum(album)

    @DeleteMapping("/{id}")
    suspend fun deleteAlbum(@PathVariable id: Long): ResponseEntity<String> {
        val albm = albumService.getAlbumById(id)
        albm?.let {
            albumService.deleteAlbum(id)
            return ResponseEntity("Successfully deleted", HttpStatus.ACCEPTED)
        }
        return ResponseEntity("Album with ID not found", HttpStatus.NOT_FOUND)
    }
}