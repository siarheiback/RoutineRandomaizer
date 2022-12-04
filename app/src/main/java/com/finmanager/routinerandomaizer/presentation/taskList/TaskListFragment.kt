package com.finmanager.routinerandomaizer.presentation.taskList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finmanager.routinerandomaizer.R
import com.finmanager.routinerandomaizer.data.SwipeController
import com.finmanager.routinerandomaizer.databinding.FragmentTaskListBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding
    private val viewModel by viewModels<TaskListViewModel> ( )
    @Inject lateinit var adapter: TaskListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.TaskListRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.TaskListRecycler.adapter = adapter

        viewModel.readAllData.observe(viewLifecycleOwner) { list ->
            adapter.differ.submitList(list)
        }

        binding.AddNewTaskBtn.setOnClickListener(){
            viewModel.newTask(
                Task(
                    0,
                    binding.NewTaskText.text.toString(),
                    null
                )
            )
            binding.NewTaskText.text = null
        }
    }


}