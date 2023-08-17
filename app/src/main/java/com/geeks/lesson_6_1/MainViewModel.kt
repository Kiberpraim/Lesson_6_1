package com.geeks.lesson_6_1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geeks.lesson_6_1.model.Task

class MainViewModel : ViewModel() {

    private var arrayList = ArrayList<Task>()
    private var _list = MutableLiveData<ArrayList<Task>>()
    val list: LiveData<ArrayList<Task>> = _list
    private var tsil = arrayListOf<Task>(Task("_sdfsd","_sdfsdfsdf"), Task("_dsdfsfd","_sdfsdf"))

    fun addTask(task: Task) {
        arrayList.add(task)
        arrayList.add(task)
        _list.value = arrayList
        Log.e("kiber", list.value.toString())
        Log.e("kiber", tsil.toString())
    }

    fun deleteTask(task: Task) {
        arrayList.removeIf { it.title == task.title }
    }
}