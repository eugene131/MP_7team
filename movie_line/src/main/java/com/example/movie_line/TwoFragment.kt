package com.example.movie_line

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_line.databinding.FragmentOneBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TwoFragment : Fragment() {
    internal var movie_info: MovieInfo? = null
    lateinit var datas: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val db = Firebase.firestore
        val datas = mutableListOf<String>()
        val movie_lines_list = mutableListOf<MovieInfo>()
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        val get_movies = db.collection("movieLinesCollection").get()
            .addOnSuccessListener {
                    documents ->
                if (documents != null){
                    for (document1 in documents) {
                        //Log.d("test firebase", "${document1.id} => ${document1.getString("movie_name")}")
//                        datas.add("${document1.getString("movie_name")}")
                        val myObject = document1.toObject(MovieInfo::class.java)
                        Log.d("test firebase1", myObject.movie_name.toString())
                        movie_lines_list.add(myObject)
                        //movie_names_list.add(document1.getString("movie_name").toString())
                    }
                }
                Log.d("test firebase2", movie_lines_list.size.toString())

                for (i in 0 until movie_lines_list.size) {
                    //datas.add(movie_lines_list[i].movie_name.toString())
                    datas.add("${movie_lines_list[i].movie_name} (${movie_lines_list[i].year})")
                }

                val layoutManager = LinearLayoutManager(activity)
                binding.recyclerview.layoutManager=layoutManager
                val adapter = MyAdapter(datas)
                binding.recyclerview.adapter = adapter
                binding.recyclerview.addItemDecoration(MyDecoration(activity as Context))
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return binding.root

    }
}