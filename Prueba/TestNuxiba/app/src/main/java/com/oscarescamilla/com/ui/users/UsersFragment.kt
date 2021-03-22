package com.oscarescamilla.com.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscarescamilla.com.R
import com.oscarescamilla.com.data.remote.RemoteDataSource
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.databinding.FragmentMainBinding
import com.oscarescamilla.com.domain.RepoImpl
import com.oscarescamilla.com.ui.Adapters.UsersAdapter
import com.oscarescamilla.com.ui.VMFactory
import com.oscarescamilla.com.vo.Resource


class UsersFragment() : Fragment(), UsersAdapter.OnUserClickListener {

    // inyeccion de dependencia para pasar el repo al view model
    private val  viewModel by viewModels<UsersViewModel> { VMFactory(RepoImpl(RemoteDataSource())) }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()


        viewModel.usersResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progresBar.visibility = View.GONE
                    // pasamos la data que llega del Success de la clase Result
                    binding.rvUsers.adapter = UsersAdapter(requireContext(), result.data , this)
                }
                is Resource.Failure -> {
                    binding.progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Fallo al cargar datos", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    private fun initRecycler(){
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }


    override fun onUserClick(user: User) {
        var bundle = Bundle()
        bundle.putParcelable("user",user)
        // lanzamos el detalle y enviamos por el bundle el objeto drink
        findNavController().navigate(R.id.userDetailFragment, bundle)
    }


}

