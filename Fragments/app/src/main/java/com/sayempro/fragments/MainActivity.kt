package com.sayempro.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var switchFragment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val firstFragment = FirstFragment()
        fragmentTransaction.add(R.id.frame, firstFragment)

        fragmentTransaction.commit()

        switchFragment = findViewById(R.id.buttonSwitchFragment)

        switchFragment.setOnClickListener {
            val fragmentManager2: FragmentManager = supportFragmentManager
            val fragmentTransaction2: FragmentTransaction = fragmentManager2.beginTransaction()

            val secondFragment = SecondFragment()
            fragmentTransaction2.replace(R.id.frame, secondFragment)
            fragmentTransaction2.commit()

        }

    }
}