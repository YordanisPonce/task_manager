package com.example.testing.gestordetareas.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*

class Homework(
    private var id: Int,
    expireDate: LocalDate,
    private var description: String,
    private var title: String
) :
    Comparable<Homework> {
    private var expireDate: LocalDate = expireDate
        get() = expireDate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun compareTo(homework: Homework): Int {
        return when {
            this.expireDate > homework.expireDate -> {
                1
            }
            this.expireDate < homework.expireDate -> {
                -1
            }
            else -> {
                0
            }
        }
    }

    override fun toString(): String {
        return "Tarea con id $id y titulo $title"
    }
}