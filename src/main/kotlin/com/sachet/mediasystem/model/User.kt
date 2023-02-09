package com.sachet.mediasystem.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("user")
data class User(
    @Id
    var id: Long? = null,
    var name: String ?= null
)