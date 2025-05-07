package com.aryama0073.kabarharian.ui.screen

import androidx.lifecycle.ViewModel
import com.aryama0073.kabarharian.model.Catatan

class MainViewModel : ViewModel() {
    val data = listOf(
        Catatan(
            1,
            "Arya",
            "Sangat Baik",
            "Hari ini happy",
            "2025-05-01 12:34:56"
        ),
        Catatan(
            2,
            "Adam",
            "Kurang Baik",
            "Ada Assessment hari ini",
            "2025-05-02 01:23:04"
        ),
        Catatan(
            3,
            "Farhan",
            "Baik",
            "Hari ini terasa biasa saja",
            "2025-05-05 09:14:45"
        ),
        Catatan(
            4,
            "Dany",
            "Baik",
            "Temanku datang ke kosku",
            "2025-05-06 10:44:16"
        ),
        Catatan(
            5,
            "Ilham",
            "Kurang Baik",
            "Aku sakit hari ini",
            "2025-05-02 15:35:29"
        )

    )
}