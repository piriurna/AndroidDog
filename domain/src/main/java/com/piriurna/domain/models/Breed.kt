package com.piriurna.domain.models

import com.piriurna.domain.models.BreedImage.Companion.mockImages

data class Breed(
    val id : Int,
    val name : String,
    val category : String, // replaced with description
    val origin : String,
    val temperament : String,
    val image: BreedImage
) {

    fun getRelativeHeight(width : Int) : Int{
        return image.getRelativeHeight(width)
    }

    fun getRelativeWidth(height : Int) : Int{
        return image.getRelativeWidth(height)
    }

    companion object {
        val mockBreeds = listOf(
            Breed(
                id = 0,
                name = "Breed 1",
                image = mockImages[0],
                temperament = "temperament",
                origin = "origin",
                category = "category"
            ),
            Breed(
                id = 1,
                name = "Breed 2",
                image = mockImages[1],
                temperament = "temperament",
                origin = "origin",
                category = "category"
            ),
            Breed(
                id = 2,
                name = "Breed 3",
                image = mockImages[2],
                temperament = "temperament",
                origin = "origin",
                category = "category"
            )
        )
    }
}