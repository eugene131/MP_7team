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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOneBinding.inflate(inflater, container, false)
        val db = Firebase.firestore
        val datas = mutableListOf<String>()
        val movie_names_list = mutableListOf<String?>()
        val users = db.collection("movieLinesCollection").get()
            .addOnSuccessListener {
                    document ->
                if (document != null){
                    for (document1 in document) {
                        Log.d("test firebase", "${document1.id} => ${document1.getString("movie_name")}")
                        movie_names_list.add(document1.getString("movie_name"))
//                        datas.add("${document1.getString("movie_name")}")
//                        val myObject = document1.toObject(MovieInfo::class.java)
//                        Log.d("object test", "${myObject.imageName}")
                        //movie_names_list.add(document1.getString("movie_name").toString())
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

//        db.collection("movieLinesCollection")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val movieLines = document.toObject(MovieInfo::class.java)
//                    Log.d(TAG, "${document.id} => test")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents: ", exception)
//            }

// error
        //Log.d("test data", movie_names_list[0].toString())
        for (i in 0..2) {
            datas.add("ddd")
        }
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.layoutManager=layoutManager
        val adapter=MyAdapter(datas)
        binding.recyclerview.adapter=adapter
        binding.recyclerview.addItemDecoration(MyDecoration(activity as Context))

        return binding.root
    }


}