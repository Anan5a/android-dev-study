package com.sayempro.fragmenttofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class SecondFragment : Fragment() {
    lateinit var tvData: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        tvData = view.findViewById(R.id.textViewRecv)
        val data = requireArguments().getString("data").toString() ?: "No data"
        tvData.text = data
        return view
    }

}