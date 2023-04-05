package com.gunjan.newsfeed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            NavigationRedirection.navigateToFragment(it, R.id.action_register_to_set_password)
        }

        binding.btnBack.setOnClickListener { NavigationRedirection.navigateBack(it) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}