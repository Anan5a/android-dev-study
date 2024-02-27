package com.sayempro.checkboxes

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var linearLayout: LinearLayout
    lateinit var green: RadioButton
    lateinit var red: RadioButton
    lateinit var yellow: RadioButton
    lateinit var changeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = findViewById(R.id.LinearLayout)
        green = findViewById(R.id.radio_green)
        red = findViewById(R.id.radio_red)
        yellow = findViewById(R.id.radio_yellow)
        changeBtn = findViewById(R.id.button_cng)


        changeBtn.setOnClickListener {
            if (green.isChecked) {
                linearLayout.setBackgroundColor(Color.GREEN)
            }
            if (red.isChecked) {
                linearLayout.setBackgroundColor(Color.RED)
            }
            if (yellow.isChecked) {
                linearLayout.setBackgroundColor(Color.YELLOW)
            }
        }

//        male = findViewById(R.id.checkBoxMale)
//        female = findViewById(R.id.checkBoxFemale)
//        text = findViewById(R.id.textView)
//
//        male.setOnClickListener {
//            if (male.isChecked) {
//                text.text = "Your gender is male!"
//                female.isChecked = false
//            } else
//                text.text = "What is your gender?"
//        }
//        female.setOnClickListener {
//            if (female.isChecked) {
//                text.text = "Your gender is female!"
//                male.isChecked = false
//            } else
//                text.text = "What is your gender?"
//        }
    }
}