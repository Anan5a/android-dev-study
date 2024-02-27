package com.sayempro.todolist

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var add: Button
    lateinit var todo: EditText
    lateinit var view: ListView

    var itemlist = arrayListOf<String>()
    val saveTodoHelper = SaveTodoHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add = findViewById(R.id.button)
        todo = findViewById(R.id.editTextText)
        view = findViewById(R.id.listView)


        itemlist = saveTodoHelper.readData(this)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemlist)
        view.adapter = adapter

        add.setOnClickListener {
            val text = todo.text.toString()
            itemlist.add(text)
            todo.text.clear()
            saveTodoHelper.writeData(itemlist, applicationContext)
            adapter.notifyDataSetChanged()
        }

        view.setOnItemClickListener { parent, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete this task?")
            alert.setMessage("This item will be permanently deleted!")
            alert.setCancelable(false)
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                itemlist.removeAt(position)

                saveTodoHelper.writeData(itemlist, applicationContext)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Item deleted", Toast.LENGTH_LONG).show()

            })
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alert.show()
        }

    }



}