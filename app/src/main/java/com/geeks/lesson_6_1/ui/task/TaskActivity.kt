package com.geeks.lesson_6_1.ui.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.geeks.lesson_6_1.MainViewModel
import com.geeks.lesson_6_1.databinding.ActivityTaskBinding
import com.geeks.lesson_6_1.model.Task
import com.geeks.lesson_6_1.ui.main.MainActivity

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initClick()
    }

    private fun initClick() {
        binding.btnSave.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString()
            )
            viewModel.addTask(data)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}