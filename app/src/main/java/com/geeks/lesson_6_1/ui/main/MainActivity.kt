package com.geeks.lesson_6_1.ui.main

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.geeks.lesson_6_1.MainViewModel
import com.geeks.lesson_6_1.databinding.ActivityMainBinding
import com.geeks.lesson_6_1.model.Task
import com.geeks.lesson_6_1.ui.task.TaskActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = TaskAdapter(this::onLongClickTask, this::isCheckedTask)

    // Объявляем переменную для хранения resultLauncher
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerView.adapter = adapter

        // Инициализируем resultLauncher с использованием registerForActivityResult
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val dataIntent: Intent? = result.data
                    // Вызываем функцию для обработки результата
                    handleActivityResult(dataIntent)
                }
            }

        initView()
        initClick()
    }

    private fun initClick() {
        binding.fab.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun handleActivityResult(intent: Intent?) { // функция для оброботки результата (из TaskActivity)
        if (intent != null) {
            val getTitle = intent.getStringExtra(TITLE_KEY)
            val getDescription = intent.getStringExtra(DESCRIPTION_KEY)
            if (getTitle != null) {
                val data = Task(
                    title = getTitle,
                    description = getDescription,
                    checkBox = false
                )
                viewModel.addTask(data)
            }
        }
    }

    private fun initView() {
        viewModel.list.observe(this) { updatedList ->
            adapter.setList(updatedList)
            adapter.notifyDataSetChanged() // Обновляем адаптер после изменения списка
        }
    }

    private fun onLongClickTask(task: Task) { // deleteTaskClick
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Вы хотите удалить данные?")
            .setMessage("Востановить данные бедет невозможным!")
            .setPositiveButton("ОК") { dialog: DialogInterface, _: Int ->
                // Обработка нажатия кнопки "OK"
                viewModel.deleteTask(task)
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog: DialogInterface, _: Int ->
                // Обработка нажатия кнопки "Отмена"
                dialog.dismiss()
            }
        dialogBuilder.show()
    }

    private fun isCheckedTask(task: Task, isChecked: Boolean) {
        viewModel.checkedTask(task,isChecked)
    }
    companion object{
        const val TITLE_KEY ="title.key"
        const val DESCRIPTION_KEY ="description.key"
    }
}