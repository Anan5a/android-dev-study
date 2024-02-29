package com.sayempro.datatransferfragmentandactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        etName = view.findViewById(R.id.editTextText)
        etEmail = view.findViewById(R.id.editTextTextEmailAddress)
        submitButton = view.findViewById(R.id.buttonSend)

        submitButton.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            (activity as MainActivity).takeData(name, email)
//            val mainActivity: MainActivity = activity as MainActivity
//            mainActivity.takeData(name, email)
        }

        return view
    }

}