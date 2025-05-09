package com.aryama0073.kabarharian.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryama0073.kabarharian.database.CatatanDao
import com.aryama0073.kabarharian.model.Catatan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: CatatanDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(nama: String, kabar: String, isi: String) {
        val catatan = Catatan(
            tanggal = formatter.format(Date()),
            nama = nama,
            kabar = kabar,
            catatan = isi
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(catatan)
        }
    }

    suspend fun getCatatan(id: Long): Catatan? {
        return dao.getCatatanById(id)
    }

    fun update(id: Long, nama: String, kabar: String, isi: String) {
        val catatan = Catatan(
            id      = id,
            tanggal = formatter.format(Date()),
            nama    = nama,
            kabar   = kabar,
            catatan = isi
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(catatan)
        }
    }

    fun permDelete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }

    fun restore(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.restore(
                id)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.softDelete(id)
        }
    }
}