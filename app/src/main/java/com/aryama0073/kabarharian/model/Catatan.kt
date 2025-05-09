package com.aryama0073.kabarharian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catatan")
data class Catatan (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val kabar: String,
    val catatan: String,
    val tanggal: String
)