package com.example.idcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idcard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddContact.setOnClickListener {
            Toast.makeText(this, "add contact!!", Toast.LENGTH_SHORT).show()
        }

        binding.btnViewDetail.setOnClickListener {
            Toast.makeText(this, "view detail", Toast.LENGTH_SHORT).show()
        }

        binding.btnMoreFunc.setOnClickListener {
            Toast.makeText(this, "more function", Toast.LENGTH_SHORT).show()
        }

        val data:MutableList<User> = loadData()
        var adapter = CustomAdapter()
        adapter.listData = loadData()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun loadData() : MutableList<User> {
        val data:MutableList<User> = mutableListOf()

        for (no in 1..100) {
            val name = "홍길동 ${no + 1}"
            val description = "홍길동 ${no}의 연락처입니다."
            val user = User(no, name, description)

            data.add(user)
        }

        return data
    }
}