package com.finmanager.routinerandomaizer.presentation.taskList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.finmanager.routinerandomaizer.R
import com.finmanager.routinerandomaizer.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    companion object {
        fun newInstance() = TaskListFragment()
    }
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var viewModel: TaskListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        val navController = findNavController()
        val config = AppBarConfiguration(navController.graph)
        binding.ListToolbar.setupWithNavController(navController,config)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.addNewTask ->  findNavController().navigate(R.id.action_taskListFragment_to_singleTaskFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}