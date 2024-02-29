package com.sayempro.fragmenttofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {
    lateinit var etData: EditText
    lateinit var sendBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        etData = view.findViewById(R.id.editTextText)
        sendBtn = view.findViewById(R.id.buttonSend)

        sendBtn.setOnClickListener {
            val data = etData.text.toString()
            val bundle = Bundle()
            bundle.putString("data", data)
            val fragment = SecondFragment()
            fragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
        }
        return view
    }

}