package com.oscarescamilla.com.ui.posts

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.oscarescamilla.com.R
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.data.remote.RemoteDataSource
import com.oscarescamilla.com.databinding.FragmentPostsBinding
import com.oscarescamilla.com.domain.RepoImpl
import com.oscarescamilla.com.ui.Adapters.CommentsAdapter
import com.oscarescamilla.com.ui.Adapters.PostsAdapter
import com.oscarescamilla.com.ui.VMFactory
import com.oscarescamilla.com.vo.Resource

class PostsFragment : Fragment(), PostsAdapter.OnShowCommentsClickListener{

    private val viewModel: PostsViewModel by viewModels { VMFactory(RepoImpl(RemoteDataSource())) }

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: User

    private  lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let { data ->
            user = data.getParcelable("user")!!
            Log.i("user", user.toString() )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheet()
        setupListener()
        initRecycler()
        viewModel.getPosts(user.id)
        setupObservers()
    }

    private fun initRecycler(){
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvComments.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }


    private fun setupListener(){
        binding.btnClose.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }


    private fun initBottomSheet(){
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet);
    }



    private fun setupObservers(){
        viewModel.postsResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progresBar.visibility = View.GONE

                    // pasamos la data que llega del Success de la clase Result
                    binding.rvPosts.adapter = PostsAdapter(requireContext(), result.data , user, this)
                }
                is Resource.Failure -> {
                    binding.progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Fallo al cargar datos", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.commentsResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progresBarComments.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.e("result", result.data.toString())
                    binding.progresBarComments.visibility = View.GONE
                    binding.rvComments.adapter = CommentsAdapter(requireContext(), result.data)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
                is Resource.Failure -> {
                    binding.progresBarComments.visibility = View.GONE
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    Toast.makeText(requireContext(), "Fallo al cargar datos", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun showComments(post: Post) {
        viewModel.getCommentsByPost(post.id)

    }





}