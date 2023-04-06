package com.gunjan.newsfeed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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
            val actionExploreToProductList =
                RegistrationFragmentDirections.actionRegisterToSetPassword(
                    binding.textInputEditTextFullName.text.toString(),
                    binding.textInputEditTextPhone.text.toString(),
                    binding.textInputEditTextAddress.text.toString(),
                    binding.textInputEditTextEmail.text.toString()
                )
            binding.root.findNavController().navigate(actionExploreToProductList)
        }

        binding.btnBack.setOnClickListener { NavigationRedirection.navigateBack(it) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}