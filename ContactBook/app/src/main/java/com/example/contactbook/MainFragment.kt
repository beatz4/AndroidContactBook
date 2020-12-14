package com.example.contactbook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactbook.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mainActivity : MainActivity? = null
    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_main, container, false)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnAddContact.setOnClickListener {
            Toast.makeText(activity?.baseContext, "btnAddContact", Toast.LENGTH_LONG).show()
        }

        binding.btnViewDetail.setOnClickListener {
            Toast.makeText(activity?.baseContext, "btnViewDetail", Toast.LENGTH_LONG).show()
        }

        binding.btnMoreFunc.setOnClickListener {
            Toast.makeText(activity?.baseContext, "btnMoreFunc", Toast.LENGTH_LONG).show()
        }

        // recycler view
        val data:MutableList<User> = loadData()
        var adapter = CustomAdapter(mainActivity)
        adapter.listData = loadData()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)

        binding.textCount.text = "저장된 연락처 : ${data.size}개"

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private fun loadData() : MutableList<User> {
        val data:MutableList<User> = mutableListOf()

        for (no in 1..100) {
            val name = "홍길동 ${no}"
            val description = "홍길동 ${no}의 연락처입니다."
            val user = User(no, name, description)

            data.add(user)
        }

        return data
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}