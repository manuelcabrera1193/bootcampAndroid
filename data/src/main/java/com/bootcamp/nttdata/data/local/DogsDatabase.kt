package com.bootcamp.nttdata.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bootcamp.nttdata.data.local.dao.DogDao
import com.bootcamp.nttdata.data.local.entities.ItemDogEntity

@Database(entities = [ ItemDogEntity::class], version = 1)
abstract class DogsDatabase : RoomDatabase(), IDogsDatabase