package com.example.testing.gestordetareas.data.database

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testing.gestordetareas.core.DatabaseHerlper
import com.example.testing.gestordetareas.data.model.Homework


class HomeworkProvider {


    @RequiresApi(Build.VERSION_CODES.O)
    private var homeworks = DatabaseHerlper.getHomework()

    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): List<Homework> {
        return homeworks
    }
}