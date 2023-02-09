package com.sachet.mediasystem.service

import com.sachet.mediasystem.model.User
import com.sachet.mediasystem.repository.UserRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val albumService: AlbumService
){
    suspend fun createUser(user: User): User {
        return userRepository.save(user)
    }

    suspend fun getAllUsers(): List<User> {
        return userRepository.findAll().toList()
    }

    suspend fun deleteUser(userId: Long) {
        albumService.deleteAlbumsByUserId(userId)
        userRepository.deleteById(userId)
    }

}