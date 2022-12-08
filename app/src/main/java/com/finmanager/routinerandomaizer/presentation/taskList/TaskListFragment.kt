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
    private lateinit var adapter: TaskListRecyclerAdapter

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

        viewModel.state.observe(viewLifecycleOwner){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }
        adapter = TaskListRecyclerAdapter(object :TaskListActionListener{
            override fun deleteTask(task: Task) {
                viewModel.deleteTask(task)
                Toast.makeText(requireContext(),task.name,Toast.LENGTH_SHORT).show()
            }
        }
        )
        val recyclerView = binding.TaskListRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.TaskListRecycler.adapter = adapter

        viewModel.readAllData.observe(viewLifecycleOwner) { list ->
            adapter.differ.submitList(list)
        }

        binding.AddNewTaskBtn.setOnClickListener(){
            viewModel.create1000sleepingTasks()
//            viewModel.newTask(
//                Task(id = 0,
//                    name = binding.NewTaskText.text.toString(),
//                    description = null,
//                    isSleeping = false,
//                    isActive = false,
//                    period = 1,
//                    sleepDate = null,
//                    wakeUpDate = null)
//            )
            binding.NewTaskText.text = null
        }
    }


}