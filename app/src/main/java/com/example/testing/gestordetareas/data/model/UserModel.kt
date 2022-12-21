package com.example.testing.gestordetareas.data.model

import java.util.*
import kotlin.collections.ArrayList

class User(
    val id: Int,
    val username: String,
    private val email: String,
    private val admin_id: Int?,
    private val role_id: Int
) {
    private val homeworks: ArrayList<Homework> = ArrayList()

    //Temporal cont
    private var cont: Int = 0

    fun addTask(title: String?, expireDate: Date?, description: String?) {
        homeworks.add(Homework(++cont, title!!, expireDate!!, description!!))
        orderTasks(homeworks, 0, homeworks.size - 1)
    }

    fun getHomeworkById(id: Int): Homework? {
        for (homework in homeworks) {
            if (homework.id === id) {
                return homework
            }
        }
        return null
    }

    fun removeHomework(date: Date?): Homework? {
        return null
    }

    fun getRole(roles: Array<Role>): Role? {
        for (role in roles) {
            if (role.id === role_id) {
                return role
            }
        }
        return null
    }

    private fun orderTasks(homeworks: ArrayList<Homework>, left: Int, rigth: Int) {
        val pivot = homeworks[left]
        var i = left
        var j = rigth
        while (i < j) {
            while (homeworks[i] <= pivot && i < j) {
                i++
            }
            while (homeworks[j] > pivot) {
                j--
            }
            if (i < j) {
                val aux = homeworks[i]
                homeworks[i] = homeworks[j]
                homeworks[j] = aux
            }
        }
        homeworks[left] = homeworks[j]
        homeworks[j] = pivot
        if (left < j - 1) {
            orderTasks(homeworks, left, j - 1)
        }
        if (rigth > i + 1) {
            orderTasks(homeworks, i + 1, rigth)
        }
    }

    override fun toString(): String {
        return username
    }

}