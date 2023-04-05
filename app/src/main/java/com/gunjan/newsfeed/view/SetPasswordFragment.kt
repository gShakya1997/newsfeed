package com.gunjan.newsfeed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.databinding.FragmentRegistrationBinding
import com.gunjan.newsfeed.databinding.FragmentSetPasswordBinding

class SetPasswordFragment : Fragment() {
    private var _binding: FragmentSetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSetPasswordBinding.inflate(inflater, container, false)
        binding.btnRegister.setOnClickListener { v ->
            Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show()
            NavigationRedirection.navigateToFragment(
                v,
                R.id.action_setPasswordFragment_to_getStartedFragment
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