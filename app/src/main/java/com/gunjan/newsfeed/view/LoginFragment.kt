package com.gunjan.newsfeed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            NavigationRedirection.navigateToFragment(
                it,
                R.id.action_loginFragment_to_dashboardActivity
            )
        }

        binding.btnBack.setOnClickListener { NavigationRedirection.navigateBack(it) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}