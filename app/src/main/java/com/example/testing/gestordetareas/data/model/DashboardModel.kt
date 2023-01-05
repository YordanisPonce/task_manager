package com.example.testing.gestordetareas.data.model

import android.net.Uri

data class DashboardModel(
    var title: String,
    var author: String,
    var photo: Uri? = null,
    var favorite: Boolean = false,
    var description: String = ""
)