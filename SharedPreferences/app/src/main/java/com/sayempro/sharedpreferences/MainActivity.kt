package com.sayempro.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sayempro.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var count = 0

    var name: String? = null
    var longText: String? = null
    var isChecked: Boolean? = null

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonPlusOne.setOnClickListener {
            count++
            binding.buttonPlusOne.text = count.toString()
        }

    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun saveData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = binding.editTextText.text.toString()
        longText = binding.editTextMultiline.text.toString()
        isChecked = binding.checkBoxRemember.isChecked

        val editor = sharedPreferences.edit()
        editor.putBoolean("isChecked", isChecked!!)
        editor.putString("name", name)
        editor.putString("longText", longText)
        editor.putInt("count", count)

        editor.apply()

        Toast.makeText(this@MainActivity, "Data was saved!", Toast.LENGTH_LONG).show()
    }


    fun loadData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        name = sharedPreferences.getString("name", null)
        longText = sharedPreferences.getString("longText", null)
        count = sharedPreferences.getInt("count", 0)
        isChecked = sharedPreferences.getBoolean("isChecked", false)


        binding.editTextText.setText(name)
        binding.editTextMultiline.setText(longText)
        binding.buttonPlusOne.text = count.toString()
        binding.checkBoxRemember.isChecked = isChecked as Boolean




    }
}
