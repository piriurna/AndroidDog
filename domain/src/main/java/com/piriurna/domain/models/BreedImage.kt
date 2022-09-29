package com.piriurna.domain.models

data class BreedImage(
    val id : String,
    val height: Int,
    val url: String,
    val width: Int
) {

    fun getRelativeHeight(width: Int) : Int {
        val heightRatio = this.height.toFloat() / this.width.toFloat()
        return (width * heightRatio).toInt()
    }
    companion object {
        val mockImages = listOf(
            BreedImage(
                id = "0",
                url = "https://cdn2.thedogapi.com/images/rkiByec47.jpg",
                height = 201,
                width = 300,
            ),
            BreedImage(
                id = "1",
                url = "https://cdn2.thedogapi.com/images/1-7cgoZSh.jpg",
                height = 200,
                width = 300,
            ),
            BreedImage(
                id = "2",
                url = "https://cdn2.thedogapi.com/images/26pHT3Qk7.jpg",
                height = 235,
                width = 300,
            ),
        )
    }
}