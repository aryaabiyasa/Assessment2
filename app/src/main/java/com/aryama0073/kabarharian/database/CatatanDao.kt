package com.aryama0073.kabarharian.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aryama0073.kabarharian.model.Catatan
import kotlinx.coroutines.flow.Flow

@Dao
interface CatatanDao {

    @Insert
    suspend fun insert(catatan: Catatan)

    @Update
    suspend fun update(catatan: Catatan)

    @Query("SELECT * FROM catatan WHERE isDeleted = 1 ORDER BY id ASC")
    fun getDeleteCatatan(): Flow<List<Catatan>>

    @Query("SELECT * FROM catatan WHERE isDeleted = 0 ORDER BY id ASC")
    fun getIsiCatatan(): Flow<List<Catatan>>

    @Query("SELECT * FROM catatan ORDER BY tanggal DESC")
    fun getCatatan(): Flow<List<Catatan>>

    @Query("SELECT * FROM catatan WHERE id = :id")
    suspend fun getCatatanById(id: Long): Catatan?

    @Query("DELETE FROM catatan WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE catatan SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Long)

    @Query("UPDATE catatan SET isDeleted = 0 WHERE id = :id")
    suspend fun restore(id: Long)
}