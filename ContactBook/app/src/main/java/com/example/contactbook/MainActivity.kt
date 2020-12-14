package com.example.contactbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment())?.commit()
    }

    fun goDetail() {
        val detailFragment = DetailFragment()
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, DetailFragment()).addToBackStack("detail").commit()
    }
}