package com.example.testing.gestordetareas.data.model

class DashboarbProvider {

    companion object {
        var f = emptyList<DashboardModel>()
        fun getFavorites() = f
    }
}