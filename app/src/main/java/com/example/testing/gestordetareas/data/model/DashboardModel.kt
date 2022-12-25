package com.example.testing.gestordetareas.data.model

data class DashboardModel(
    var title: String,
    var author: String,
    var photo: String? = null,
    var favorite: Boolean = false
)