package com.oscarescamilla.com.ui.todos

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.oscarescamilla.com.data.model.todo.Todo
import com.oscarescamilla.com.data.model.todo.TodoBody
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.data.remote.RemoteDataSource
import com.oscarescamilla.com.databinding.FragmentTodosBinding
import com.oscarescamilla.com.domain.RepoImpl
import com.oscarescamilla.com.ui.Adapters.TodosAdapter
import com.oscarescamilla.com.ui.VMFactory
import com.oscarescamilla.com.vo.Resource



class TodosFragment : Fragment(){



    private val viewModel: TodosViewModel by viewModels { VMFactory(RepoImpl(RemoteDataSource())) }

    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: User
    private lateinit var adapter: TodosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let { data ->
            user = data.getParcelable("user")!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        initRecycler()
        viewModel.getTodos(user.id)
        setupObservers()
    }


    private fun initRecycler(){
        binding.rvTodos.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvTodos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }


    private fun setupListeners(){
        val  bsb = BottomSheetBehavior.from(binding.bottomSheet);
        binding.fabAddTodo.setOnClickListener {
            //findNavController().navigate(R.id.addTodoFragment)
            bsb.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.btnSave.setOnClickListener {

            if (!TextUtils.isEmpty(binding.etTitleTodo.text.toString())){


                val todoBody = TodoBody(
                    binding.cbCompletedTodo.isChecked,
                    binding.etTitleTodo.text.toString(),
                    user.id
                )
                bsb.state = BottomSheetBehavior.STATE_HIDDEN
                viewModel.addTodo(todoBody)
                clearFormAddTodo()

            }else{
                binding.etTitleTodo.error = "Add a title for the task"
            }

        }

        binding.btnCancel.setOnClickListener {
            clearFormAddTodo()
            bsb.state = BottomSheetBehavior.STATE_HIDDEN
        }

        binding.btnClose.setOnClickListener {
            clearFormAddTodo()
            bsb.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    fun ocultar() {

        val vieww = activity?.currentFocus
        if (vieww != null) {
            //Aquí esta la magia
            val input =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(vieww.windowToken, 0)

        }
    }

    private fun clearFormAddTodo(){
        binding.etTitleTodo.text.clear()
        binding.cbCompletedTodo.isChecked = false



    }








    private fun setupObservers(){
        viewModel.todosResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progresBar.visibility = View.GONE
                    // pasamos la data que llega del Success de la clase Result
                    adapter = TodosAdapter(requireContext(), result.data as MutableList<Todo>)
                    binding.rvTodos.adapter = adapter
                }
                is Resource.Failure -> {
                    binding.progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Fallo al cargar datos", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        viewModel.addTodoResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    //binding.progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {

                    adapter.addTodo(result.data)

                    Snackbar.make(binding.root, "New ToDo added", Snackbar.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    binding.progresBar.visibility = View.GONE
                    Snackbar.make(binding.root, "An error has occurred", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }




}