package com.sayempro.fragmentslist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment


class MyListFragment : ListFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter =
            activity?.let {
                ArrayAdapter.createFromResource(
                    it,
                    R.array.cities,
                    android.R.layout.simple_list_item_1
                )
            }
        listAdapter = arrayAdapter
        listView.setOnItemClickListener { parent, view2, position, id ->
            val intent = Intent(activity, SecondActivity::class.java)
            intent.putExtra("index", position)
            startActivity(intent)
        }
    }
}