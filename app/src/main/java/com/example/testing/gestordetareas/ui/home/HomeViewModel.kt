package com.example.testing.gestordetareas.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testing.gestordetareas.data.model.DashboardModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val board = MutableLiveData<DashboardModel>().apply {
        value = null
    }
    val elements = MutableLiveData<MutableList<DashboardModel>>().apply {
        value = mutableListOf()
    }
    val empty = MutableLiveData<Boolean>().apply {
        value = true
    }

    val elementDeleted = MutableLiveData<Int>().apply {
        value = -1
    }
    val text: LiveData<String> = _text


    fun setValues(it: MutableList<DashboardModel>) {
        elements.postValue(it)
    }

    fun addBoard(element: DashboardModel) {
        this.board.postValue(element)
        Log.i("e","LLegue aqui")
        empty.postValue(false)
    }

    fun notifyElementDeleted(position:Int){
        this.elementDeleted.postValue(position)
    }


}