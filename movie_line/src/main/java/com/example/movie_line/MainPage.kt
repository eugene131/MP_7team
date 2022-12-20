package com.example.movie_line

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie_line.databinding.ActivityMainPageBinding
import com.google.android.datatransport.cct.internal.QosTier
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main_page.*


class MainPage : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    val tabNames = listOf("드라마", "영화", "all")
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened,
            R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        view_activity_movie()


//        navigationView = findViewById(R.id.mainDrawer)
//        navigationView.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.myPage -> {
//                    goToMyPage()
//                    true
//                }
//                else -> false
//            }
//        }
//
//        val adapter = MyFragmentPagerAdapter(this)
//        binding.viewpager.adapter = adapter
//        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
//            tab.text = tabNames[position]
//        }.attach()

        binding.fab.setOnClickListener {
            //val intent = Intent(this, AddActivity::class.java)
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    fun Create_movie_list(): ArrayList<movie_list> {

        var movieList = arrayListOf<movie_list>()

        val db = Firebase.firestore
        val movie_lines_list = mutableListOf<MovieInfo>()

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
                    movieList.add(movie_list(name = movie_lines_list[i].movie_name.toString(), year = movie_lines_list[i].year.toString(), photo = ""))
                }

                Log.d("test firebase3", movieList.size.toString())
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
        return movieList
    }

    fun view_activity_movie() {//영화 리스트뷰를 보여줌 ================= fixing ==============
        var movieList = arrayListOf<movie_list>()

        val db = Firebase.firestore
        val movie_lines_list = mutableListOf<MovieInfo>()

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
                    movieList.add(movie_list(name = movie_lines_list[i].movie_name.toString(), year = movie_lines_list[i].year.toString(), photo = movie_lines_list[i].movie_line.toString()))
                }

                val movieAdapter = MainListAdapter(this, movieList)//MainListAdapter를 사용하여 뷰와 이 context를 연결
                mainListView.adapter = movieAdapter //여기까지가 movie리스트를 화면에 뛰우는 작업

                //listView의 item을 클릭하면 화면 전환하는 작업 ============fixing===========
                mainListView.setOnItemClickListener { parent, view, position, id ->
                    val item = parent.getItemAtPosition(position) as movie_list//선택한 목록의 정보 받아오기
//            val clickedmovie = movieList[position]
//            // 선택된 목록정보를 가져왔으면 이제 화면 이동
//            val myIntent = Intent(this, test_detail(this,item.name,item.year)::class.java)//다른 액티비티에 연결하기 위하 Intent
//            myIntent.putExtra("movie_info", clickedmovie)
//            System.out.println(position)
//            // 화면 전환
//            startActivity(myIntent)
//                    Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
                    goToLinePage(item.name, item.photo)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    fun goToMyPage() {
        val intent = Intent(this, MyPageActivity::class.java)
        startActivity(intent)
    }

    fun goToLinePage(movieName: String, movieLine: String) {
        val intent = Intent(this, QuoteActivity::class.java)
        intent.putExtra("movieName", movieName)
        intent.putExtra("movieLine", movieLine)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


