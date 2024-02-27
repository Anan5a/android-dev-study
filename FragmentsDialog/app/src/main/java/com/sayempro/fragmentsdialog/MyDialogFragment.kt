package com.sayempro.fragmentsdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {

    lateinit var name: EditText
    lateinit var age: EditText
    lateinit var cancelBtn: Button
    lateinit var okBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_dialog, container, false)
        name = view.findViewById(R.id.editTextName)
        age = view.findViewById(R.id.editTextAge)
        cancelBtn = view.findViewById(R.id.buttonCancel)
        okBtn = view.findViewById(R.id.buttonOK)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        okBtn.setOnClickListener {
            val name: String = name.text.toString()
            val age: Int = age.text.toString().toInt()
            val mainActivity: MainActivity = activity as MainActivity
            mainActivity.getUserData(name, age)
            dialog?.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }
        // Inflate the layout for this fragment
        return view
    }

}