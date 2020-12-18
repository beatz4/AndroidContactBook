package com.example.contactbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    fun goDetail(user: User) {

        val detailFragment = DetailFragment()
        var bundle = Bundle()
        bundle.putInt("no", user.no)
        bundle.putString("name", user.name)
        bundle.putString("description", user.description)
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, detailFragment).addToBackStack("detail").commit()
    }

    fun goBack(){
        onBackPressed()
    }
}