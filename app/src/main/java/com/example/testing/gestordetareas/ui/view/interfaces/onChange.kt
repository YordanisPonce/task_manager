package com.example.testing.gestordetareas.ui.view.interfaces

import com.example.testing.gestordetareas.data.model.DashboardModel

interface onChange {
    fun test(list:MutableList<DashboardModel>)
    fun onDelete(position:Int)
}