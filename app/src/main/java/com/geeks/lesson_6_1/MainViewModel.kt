package com.geeks.lesson_6_1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geeks.lesson_6_1.model.Task

class MainViewModel : ViewModel() {

    private var arrayList = ArrayList<Task>()
    private var _list = MutableLiveData(arrayList)
    val list: LiveData<ArrayList<Task>> = _list

    fun setTask(task: Task) {
        arrayList.add(task)
    }

    fun deleteTask(task: Task) {
        arrayList.removeIf { it.title == task.title }
    }
}