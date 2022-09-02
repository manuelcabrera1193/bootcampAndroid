package com.bootcamp.nttdata.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bootcamp.nttdata.data.local.entities.ItemDogEntity

@Dao
abstract class DogDao {

    @Query("SELECT * FROM item_god_table ORDER BY image DESC")
    abstract suspend fun getAllItemGogs() : List<ItemDogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllItemDogs(list: List<ItemDogEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertItemDogs(item: ItemDogEntity)

    @Query("DELETE FROM item_god_table")
    abstract suspend fun deleteAllDogs()

}