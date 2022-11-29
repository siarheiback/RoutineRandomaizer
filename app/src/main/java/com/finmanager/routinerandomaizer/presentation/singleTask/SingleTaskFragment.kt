package com.finmanager.routinerandomaizer.presentation.singleTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.finmanager.routinerandomaizer.databinding.FragmentSingleTaskBinding
import com.finmanager.routinerandomaizer.databinding.FragmentTaskListBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleTaskFragment : Fragment() {
    private lateinit var binding: FragmentSingleTaskBinding
    private val viewModel by viewModels<SingleTaskViewModel> ( )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveBtn.setOnClickListener(){
            val newTask = Task(
                id = 0,
                name = binding.TaskText.text.toString(),
                description = null
            )
            viewModel.newTask(newTask)
        }
    }

}