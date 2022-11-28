package com.finmanager.routinerandomaizer.presentation.singleTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finmanager.routinerandomaizer.databinding.FragmentSingleTaskBinding
import com.finmanager.routinerandomaizer.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleTaskFragment : Fragment() {
    private lateinit var binding: FragmentSingleTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleTaskBinding.inflate(inflater, container, false)
        return binding.root
    }


}