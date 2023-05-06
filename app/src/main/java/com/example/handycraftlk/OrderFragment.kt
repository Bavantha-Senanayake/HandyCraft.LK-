package com.example.handycraftlk

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.handycraftlk.R

class OrderFragment : Fragment() {

    private lateinit var btnClickHere : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_order, container, false)
        btnClickHere = view.findViewById(R.id.btnClickHere)
        btnClickHere.setOnClickListener {

            val intent = Intent(activity, COrdersFragmentMain::class.java)
            startActivity(intent)
            activity?.finish()

        }

        return view
    }


}