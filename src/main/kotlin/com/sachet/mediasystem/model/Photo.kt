package com.sachet.mediasystem.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("photo")
data class Photo(
    @Id
    var id: Long? = null,
    var name: String? = null,
    var albumId: Long? = null,
    var path: String? = null
)