package com.oscarescamilla.com.ui.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.oscarescamilla.com.R
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.databinding.FragmentUserDetailBinding



class UserDetailFragment : Fragment() {


    private lateinit var user: User

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {data ->
            user = data.getParcelable<User>("user")!!
            Log.i("user", "${user.image} ")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData(user)
        setupListeners()
    }

    private fun bindData(user: User){
        Glide.with(this).load(user.image).centerCrop().into(binding.imgUserDetail)
        binding.tvUserName.text = user.name
        binding.tvUserEmail.text = user.email
        binding.tvAddress.text = "${user.address.street}, ${user.address.suite}, ${user.address.city}"
        binding.tvPhone.text = user.phone
        binding.tvWebsite.text = user.website
        binding.tvCompany.text = user.company.name
        binding.tvCompanyDescription.text = "${user.company.catchPhrase}, ${user.company.bs}"
    }


    private fun setupListeners(){
        binding.btnTodos.setOnClickListener {
            var bundle = Bundle()
            bundle.putParcelable("user",user)
            findNavController().navigate(R.id.todosFragment, bundle)
        }

        binding.btnPosts.setOnClickListener {
            var bundle = Bundle()
            bundle.putParcelable("user",user)
            findNavController().navigate(R.id.postsFragment, bundle)
        }
    }

}







