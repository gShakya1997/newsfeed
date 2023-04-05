package com.gunjan.newsfeed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.databinding.FragmentGetStartedBinding

class GetStartedFragment : Fragment() {
    private var _binding: FragmentGetStartedBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        navController = NavigationRedirection.setupNavController(
            requireActivity(),
            R.id.fragment_container_main_activity
        )

        binding.btnGoToRegistration.setOnClickListener {
            NavigationRedirection.navigateWithNavController(
                navController,
                R.id.action_getStartedFragment_to_registrationFragment
            )
        }

        binding.btnGoToLogin.setOnClickListener {
            NavigationRedirection.navigateWithNavController(
                navController,
                R.id.action_getStartedFragment_to_loginFragment
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}