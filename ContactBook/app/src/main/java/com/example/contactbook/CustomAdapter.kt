package com.example.contactbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactbook.databinding.ItemRecyclerBinding

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.Holder>() {

    private lateinit var itemClickListener : ItemClickListener
    private lateinit var itemBinding : ItemRecyclerBinding
    var listData = mutableListOf<User>()

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : Holder {
        itemBinding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(itemBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = listData.get(position)
        holder.setUser(user)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = listData.size

    fun setItemClickListener(itemClickListener : ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    class Holder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUser(user: User) {
            binding.textNo.text = "${user.no}"
            binding.textName.text = "${user.name}"
            binding.textDescription.text = "${user.description}"
        }
    }
}

