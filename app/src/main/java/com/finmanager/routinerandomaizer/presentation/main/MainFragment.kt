package com.finmanager.routinerandomaizer.presentation.main

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.finmanager.routinerandomaizer.R
import com.finmanager.routinerandomaizer.databinding.FragmentMainBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.presentation.taskList.TaskListRecyclerAdapter
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
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

    private var mRewardedAd: RewardedAd? = null
    private final var TAG = "MainActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        initAdMob()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(requireContext(),"ca-app-pub-3940256099942544/5224354917",
            adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError.toString())
                mRewardedAd = null
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                mRewardedAd = rewardedAd
            }
        })

        mRewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                Log.d(TAG, "Ad was clicked.")
            }
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mRewardedAd = null
            }
            override fun onAdImpression() {
                Log.d(TAG, "Ad recorded an impression.")
            }
            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "end")
            }
        }


        adapter = CurrentTasksRecyclerAdapter(object : TaskActionListener{
            override fun completeTask(task: Task) {
                viewModel.completeTask(task)
            }
        }
        )

        viewModel.state.observe(viewLifecycleOwner){
            if(it){
                binding.TaskLoading.visibility = View.VISIBLE
            }else{
                binding.TaskLoading.visibility = View.GONE
            }
        }

        viewModel.taskList.observe(viewLifecycleOwner){list->
            viewModel.wake(list)
            val activeTasksList = list.filter { it.isActive }
            adapter.differ.submitList(activeTasksList)

            binding.randomizeBtn.setOnClickListener(){
                viewModel.getTask(list)
            }

            binding.declineBtn.setOnClickListener(){
                if (mRewardedAd != null) {
                    mRewardedAd?.show(requireActivity(),OnUserEarnedRewardListener() {
                        viewModel.setDefaultTask()
                    })
                } else {
                    Toast.makeText(requireContext(),getString(R.string.NoAds),Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.randomTask.observe(viewLifecycleOwner){task->
            viewModel.controller(binding.group, binding.message, task, binding.randomizeBtn)
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

    override fun onResume() {
        super.onResume()
        binding.adView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.adView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.adView.destroy()
    }

    private fun initAdMob(){
        MobileAds.initialize(requireContext())
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }


}