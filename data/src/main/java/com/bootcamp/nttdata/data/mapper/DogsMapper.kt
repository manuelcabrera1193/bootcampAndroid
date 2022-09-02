package com.bootcamp.nttdata.data.mapper

import com.bootcamp.nttdata.data.response.DogsResponse
import com.bootcamp.nttdata.models.Dogs


fun DogsResponse.toModel() = Dogs(
    images = this.message
)