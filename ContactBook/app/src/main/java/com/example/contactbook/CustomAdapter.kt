package com.example.contactbook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactbook.databinding.ItemRecyclerBinding

class CustomAdapter(context: MainActivity?) : RecyclerView.Adapter<CustomAdapter.Holder>() {

    private var mainActivity : MainActivity = context as MainActivity
    private lateinit var itemBinding : ItemRecyclerBinding
    var listData = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : Holder {
        itemBinding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(itemBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = listData.get(position)
        holder.setUser(user)

        holder.itemView.setOnClickListener {
            mainActivity.goDetail(user)
        }
    }

    override fun getItemCount(): Int = listData.size

    class Holder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUser(user: User) {
            binding.textNo.text = "${user.no}"
            binding.textName.text = "${user.name}"
            binding.textDescription.text = "${user.description}"
        }
    }
}

