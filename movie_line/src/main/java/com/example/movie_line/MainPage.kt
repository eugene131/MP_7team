package com.example.movie_line

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie_line.databinding.ActivityMainPageBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
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

        navigationView = findViewById(R.id.mainDrawer)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.myPage -> {
                    goToMyPage()
                    true
                }
                else -> false
            }
        }

        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        binding.fab.setOnClickListener {
            //val intent = Intent(this, AddActivity::class.java)
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    fun goToMyPage() {
        val intent = Intent(this, MyPageActivity::class.java)
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


