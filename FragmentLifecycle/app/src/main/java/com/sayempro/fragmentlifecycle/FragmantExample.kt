package com.sayempro.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmantExample : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("fragmant", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("fragmant", "onCreate")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("fragmant", "onViewCreated")

    }

    override fun onStart() {
        super.onStart()
        Log.i("fragmant", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.i("fragmant", "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.i("fragmant", "onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.i("fragmant", "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("fragmant", "onDestroy")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("fragmant", "onDestroyView")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("fragmant", "onDetach")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.i("fragmant", "onCreateView")

        return inflater.inflate(R.layout.fragment_new, container, false)
//        return super.onCreateView(inflater, container, savedInstanceState)

    }
}