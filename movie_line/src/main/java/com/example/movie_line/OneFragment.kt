package com.example.movie_line

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_line.databinding.FragmentOneBinding


class OneFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("드라마 명대사 $i")
        }

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.layoutManager=layoutManager
        val adapter=MyAdapter(datas)
        binding.recyclerview.adapter=adapter
        binding.recyclerview.addItemDecoration(MyDecoration(activity as Context))

        return binding.root
    }

}