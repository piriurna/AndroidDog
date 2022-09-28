package com.piriurna.domain.models

data class Breed(
    val name : String,
    val category : String, // replaced with description
    val origin : String,
    val temperament : String,
    val imageUrl: String
)