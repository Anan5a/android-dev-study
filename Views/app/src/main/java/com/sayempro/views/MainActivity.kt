package com.sayempro.views

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //    lateinit var listView: ListView
//    lateinit var recyclerView: RecyclerView
//    lateinit var cardView: CardView //we do not use card view directly!
//    lateinit var adapter: CountriesAdapter

//    var countryListWithFlagAndDetails = listOf<Map<String, Any>>(
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//        mapOf(
//            "name" to "Bangladesh",
//            "desc" to "This is the flag of Bangladesh! I was born here.",
//            "icon" to R.drawable.ic_flag_bd
//        ),
//        mapOf(
//            "name" to "United States of America",
//            "desc" to "This is the USA flag.",
//            "icon" to R.drawable.ic_flag_us
//        ),
//        mapOf(
//            "name" to "India",
//            "desc" to "This is the flag of India",
//            "icon" to R.drawable.ic_flag_in
//        ),
//
//    )
//    lateinit var gridView:GridView
//    var nameList = arrayListOf<String>()
//    var imageList = arrayListOf<Int>()
//    lateinit var adapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.scroll_layout)
//        fillArray()

//        gridView = findViewById(R.id.gridView)
//        adapter = AnimalAdapter(nameList, imageList, this@MainActivity)
//        gridView.adapter = adapter
//        gridView.setOnItemClickListener { parent, view, position, id ->
//            Toast.makeText(applicationContext, "You clicked ${nameList[position]}", Toast.LENGTH_LONG).show()
//        }
//        recyclerView = findViewById(R.id.recyclerView)
//
//        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
//        adapter = CountriesAdapter(countryListWithFlagAndDetails.shuffled(), this@MainActivity)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
//        recyclerView.layoutManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        listView = findViewById(R.id.listView)
//
//        val countreis = resources.getStringArray(R.array.countries)
//
//        val listViewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countreis)
//        listView.adapter = listViewAdapter
//        listView.setOnItemClickListener { parent, view, position, id ->
//            val selected = parent.getItemAtPosition(position).toString()
//            Toast.makeText(applicationContext, "You selected $selected", Toast.LENGTH_SHORT).show()
//        }
    }
//
//    fun fillArray(): Unit {
//        nameList.add("Bird")
//        nameList.add("Cat")
//        nameList.add("Chicken")
//        nameList.add("Dog")
//        nameList.add("Fish")
//        nameList.add("Monkey")
//        nameList.add("Rabbit")
//        nameList.add("Sheep")
//        nameList.add("Lion")
//
//        imageList.add(R.drawable.ic_flag_bd)
//        imageList.add(R.drawable.ic_flag_us)
//        imageList.add(R.drawable.ic_flag_in)
//        imageList.add(R.drawable.ic_flag_bd)
//        imageList.add(R.drawable.ic_flag_in)
//        imageList.add(R.drawable.ic_flag_us)
//        imageList.add(R.drawable.ic_flag_in)
//        imageList.add(R.drawable.ic_flag_bd)
//        imageList.add(R.drawable.ic_flag_us)
//    }
}