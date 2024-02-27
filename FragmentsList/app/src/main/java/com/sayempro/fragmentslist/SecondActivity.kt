package com.sayempro.fragmentslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val index = intent.getIntExtra("index", 0)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val secondFragment = SecondFragment()
        val bundle = Bundle()
        bundle.putInt("index", index)
        secondFragment.arguments = bundle
        fragmentTransaction.replace(R.id.frameLayout, secondFragment)

        fragmentTransaction.commit()
    }
}