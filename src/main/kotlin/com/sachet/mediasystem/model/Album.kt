package com.sachet.mediasystem.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("album")
data class Album(
    @Id
    var id: Long ?= null,
    var userId: Long ?= null,
    var albumName: String ?= null
)
