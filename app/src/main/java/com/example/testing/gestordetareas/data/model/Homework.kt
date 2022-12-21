package com.example.testing.gestordetareas.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*

class Homework(
    var id: Int,
    private val title: String,
    private val expireDate: Date,
    private val description: String
) :

    Comparable<Homework?> {
    private val feedbacks: ArrayList<Feedback> = ArrayList()
    private var cont: Int = 0
    override fun toString(): String {
        return "Titulo: $title Fecha expiracion: $expireDate Descripcion: $description"
    }

    fun addFeedback(image: String?, description: String?) {
        feedbacks.add(Feedback(++cont, image!!, description!!))
    }

    fun getFeedbacks(): ArrayList<Feedback> {
        return feedbacks
    }

    override fun compareTo(other: Homework?): Int {
        return expireDate.compareTo(other!!.expireDate)
    }
}