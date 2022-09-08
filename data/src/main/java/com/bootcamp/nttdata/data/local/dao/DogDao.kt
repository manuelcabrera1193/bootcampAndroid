package com.bootcamp.nttdata.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bootcamp.nttdata.data.local.entities.ItemDogEntity

@Dao
interface DogDao {

    @Query("SELECT * FROM item_god_table ORDER BY image DESC")
    suspend fun getAllItemGogs(): List<ItemDogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItemDogs(list: List<ItemDogEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemDogs(item: ItemDogEntity)

    @Query("DELETE FROM item_god_table")
    suspend fun deleteAllDogs()

}