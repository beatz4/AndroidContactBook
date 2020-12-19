package com.example.contactbook

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.contactbook.databinding.FragmentDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mainActivity : MainActivity? = null
    private var _binding: FragmentDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var isNew : Boolean = false

    // GALLERY
    private val PERMISSION_CODE_READ = 1001
    private val PERMISSION_CODE_WRITE = 1002
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_detail, container, false)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        if (!this.isNew) {
            var user = User()
            user.no = arguments?.getInt("no")!!
            user.name = arguments?.getString("name")!!
            user.description = arguments?.getString("description")!!

            setUserData(user)
        }

        val isVisible = if(isNew) View.VISIBLE else View.INVISIBLE
        binding.btnSave.visibility = isVisible
        binding.btnCancel.visibility = isVisible

        binding.btnBack.setOnClickListener {
            mainActivity?.goBack()
        }

        binding.btnUserPic.setOnClickListener {
            checkPermissionForImage()
        }

        binding.btnSave.setOnClickListener {

            val name = binding.editName.text.toString()
            val description = binding.editDescription.text.toString()

            mainActivity?.helper?.insertUser(name, description)
            mainActivity?.goBack()

            //Toast.makeText(activity?.baseContext, "binding.btnSave.setOnClickListener", Toast.LENGTH_LONG).show()
        }

        binding.btnCancel.setOnClickListener {
            Toast.makeText(activity?.baseContext, "binding.btnCancel.setOnClickListener", Toast.LENGTH_LONG).show()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Toast.makeText(activity?.baseContext, "call onActivityResult", Toast.LENGTH_LONG).show()

        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == REQUEST_IMAGE_CAPTURE || requestCode == IMAGE_PICK_CODE) {
                // Get image URI From intent
                var imageUri = data.data
                // do something with the image URI
                binding.btnUserPic.setImageURI(imageUri)
            }
        }
    }

    private fun setUserData(user : User) {
        binding.editName.setText(user.name)
        binding.editDescription.setText(user.description)
    }

    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((activity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (activity?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(permission, PERMISSION_CODE_READ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1001
                requestPermissions(permissionCoarse, PERMISSION_CODE_WRITE) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            } else {
                pickImageFromGallery()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE) // GIVE AN INTEGER VALUE FOR IMAGE_PICK_CODE LIKE 1000
    }

    fun setPageType(isNew : Boolean) {
        this.isNew = isNew;
    }

    companion object {

        //image pick code
        private val IMAGE_PICK_CODE = 1000;

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}