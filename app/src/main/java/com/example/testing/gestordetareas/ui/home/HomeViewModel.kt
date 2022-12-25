package com.example.testing.gestordetareas.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testing.gestordetareas.data.model.DashboardModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    private val elements = MutableLiveData<List<DashboardModel>>().apply {
        value = emptyList<DashboardModel>()
    }
    val text: LiveData<String> = _text
}