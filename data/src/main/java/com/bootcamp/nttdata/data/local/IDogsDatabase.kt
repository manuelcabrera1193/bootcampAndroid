package com.bootcamp.nttdata.data.local

import com.bootcamp.nttdata.data.local.dao.DogDao

interface IDogsDatabase {
    fun getDogsDao() : DogDao
}