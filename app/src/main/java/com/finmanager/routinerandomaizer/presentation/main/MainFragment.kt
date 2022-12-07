package com.finmanager.routinerandomaizer.presentation.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.finmanager.routinerandomaizer.R
import com.finmanager.routinerandomaizer.databinding.FragmentMainBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.presentation.taskList.TaskListRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CurrentTasksRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        adapter = CurrentTasksRecyclerAdapter(object : TaskActionListener{
            override fun completeTask(task: Task) {
                viewModel.completeTask(task)
            }

        }
        )

        viewModel.taskList.observe(viewLifecycleOwner){list->
            val activeTasksList = list.filter { it.isActive }
            adapter.differ.submitList(activeTasksList)

            binding.randomizeBtn.setOnClickListener(){
                viewModel.getTask(list)
            }
        }

        viewModel.randomTask.observe(viewLifecycleOwner){task->

            viewModel.controller(binding.group, binding.message, task)


            binding.acceptBtn.setOnClickListener(){
                if (task != null) {
                    viewModel.acceptTask(task)
                }
            }
        }

        val recyclerView = binding.CurrentTaskRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.CurrentTaskRecycler.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.itemsList ->  findNavController().navigate(R.id.action_mainFragment_to_taskListFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}