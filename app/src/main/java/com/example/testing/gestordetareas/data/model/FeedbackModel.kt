package com.example.testing.gestordetareas.data.model

data class Feedback(val id: Int, val image: String, private val description: String) {
    override fun toString(): String {
        return description
    }
}
