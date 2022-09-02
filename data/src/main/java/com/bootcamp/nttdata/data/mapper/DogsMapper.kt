package com.bootcamp.nttdata.data.mapper

import com.bootcamp.nttdata.data.local.entities.ItemDogEntity
import com.bootcamp.nttdata.data.response.DogsResponse
import com.bootcamp.nttdata.models.Dogs


fun DogsResponse.toModel() = Dogs(
    images = this.message, origin = "Api"
)


fun List<ItemDogEntity>.toModel() = Dogs(images = this.map {
    it.image
}, origin = "Local")

fun List<ItemDogEntity>.getImages() = this.map {
    it.image
}


fun Dogs.toData() = this.images.map {
    ItemDogEntity(image = it)
}
