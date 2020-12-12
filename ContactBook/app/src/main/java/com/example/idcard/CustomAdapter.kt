package com.example.idcard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.idcard.databinding.ItemRecyclerBinding

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {

    var listData = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : Holder {
        val itemBinding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)



        return Holder(itemBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = listData.get(position)
        holder.setUser(user)
    }

    override fun getItemCount(): Int = listData.size

    class Holder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "클릭 이벤트 호출!!", Toast.LENGTH_SHORT).show()
            }
        }

        fun setUser(user: User) {
            binding.textNo.text = "${user.no}"
            binding.textName.text = "${user.name}"
            binding.textDescription.text = "${user.description}"
        }
    }
}

