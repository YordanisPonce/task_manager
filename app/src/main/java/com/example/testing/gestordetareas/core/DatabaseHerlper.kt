package com.example.testing.gestordetareas.core

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testing.gestordetareas.data.model.Homework
import java.time.LocalDate
import java.util.*

object DatabaseHerlper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getHomework(): ArrayList<Homework> {
        var homeworks = ArrayList<Homework>()
        homeworks.add(Homework(1, LocalDate.of(2018, 5, 20), "Hola mundo", "Tarea view"))
        homeworks.add(Homework(1, LocalDate.of(2019, 5, 20), "Hola mundo", "Tarea view"))
        homeworks.add(Homework(1, LocalDate.of(2017, 5, 20), "Hola mundo", "Tarea view"))
        homeworks.add(Homework(1, LocalDate.of(2022, 5, 20), "Hola mundo", "Tarea view"))
        homeworks.add(Homework(1, LocalDate.of(2020, 5, 20), "Hola mundo", "Tarea view"))
        return homeworks
    }
}