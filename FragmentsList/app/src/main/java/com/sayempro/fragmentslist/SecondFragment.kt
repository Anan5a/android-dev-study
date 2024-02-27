package com.sayempro.fragmentslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    lateinit var textView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        textView = view.findViewById(R.id.fragment2TextView)
        val index: Int = arguments?.getInt("index", 0) ?: 0
        val city: String = resources.getStringArray(R.array.cities)[index]
        textView.text =
            String.format(resources.getString(R.string.fragment_2_text), "$index --> ${city}")
        // Inflate the layout for this fragment
        return view
    }

}

