package com.sayempro.fragmentdatatransfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BMIFragment : Fragment() {
    lateinit var tvBMI: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b_m_i, container, false)
        tvBMI = view.findViewById(R.id.textViewBMI)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val height = requireArguments().getDouble("height", 0.0)
        val weight = requireArguments().getDouble("weight", 0.0)
        tvBMI.text = calculateBMI(height, weight)
    }

    private fun calculateBMI(height: Double, weight: Double):String {
        val BMI: Double = ((weight * 10000) / (height * height))
        Log.d("BMI", BMI.toString())
        return String.format("Your BMI is: %.2f", BMI)
    }

}