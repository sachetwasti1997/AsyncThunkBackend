package com.sachet.mediasystem.controller

import com.sachet.mediasystem.model.User
import com.sachet.mediasystem.service.UserService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    val userService: UserService
) {
    @GetMapping("/all")
    suspend fun getAllUsers():List<User> = userService.getAllUsers()

    @PostMapping
    suspend fun createUser(@RequestBody user: User): User{
        return userService.createUser(user)
    }

    @DeleteMapping("/{userId}")
    suspend fun deleteByUserId(@PathVariable userId: Long) {
        userService.deleteUser(userId)
    }

}