package com.gunjan.newsfeed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.databinding.FragmentRegistrationBinding
import com.gunjan.newsfeed.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            if (isFullNameValid() and isPhoneValid() and isAddressValid() and isEmailValid()) {
                userViewModel.checkExistUsers(binding.textInputEditTextEmail.text.toString())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.checkUserEmail.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        if (it.data == true) {
                            binding.apply {
                                val actionExploreToProductList =
                                    RegistrationFragmentDirections.actionRegisterToSetPassword(
                                        textInputEditTextFullName.text.toString(),
                                        textInputEditTextPhone.text.toString(),
                                        textInputEditTextAddress.text.toString(),
                                        textInputEditTextEmail.text.toString()
                                    )
                                root.findNavController().navigate(actionExploreToProductList)
                            }
                        } else {
                            Toast.makeText(requireContext(), "Email already in use", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(),
                            it.message!!.asString(requireContext()), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnBack.setOnClickListener { NavigationRedirection.navigateBack(it) }

        return binding.root
    }

    private fun isFullNameValid(): Boolean {
        binding.textInputEditTextFullName.apply {
            return if (text.isNullOrBlank()) {
                error = UiText.StringResource(R.string.required).asString(requireContext())
                false
            } else {
                error = null
                true
            }
        }
    }

    private fun isPhoneValid(): Boolean {
        binding.textInputEditTextPhone.apply {
            return if (text.isNullOrBlank()) {
                error = UiText.StringResource(R.string.required).asString(requireContext())
                false
            } else if (text!!.length < 10) {
                error = UiText.StringResource(R.string.invalid_number).asString(requireContext())
                false
            } else {
                error = null
                true
            }
        }
    }

    private fun isAddressValid(): Boolean {
        binding.textInputEditTextAddress.apply {
            return if (text.isNullOrBlank()) {
                error = UiText.StringResource(R.string.required).asString(requireContext())
                false
            } else {
                error = null
                true
            }
        }
    }

    private fun isEmailValid(): Boolean {
        binding.textInputEditTextEmail.apply {
            return if (text.isNullOrBlank()) {
                error = UiText.StringResource(R.string.required).asString(requireContext())
                false
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()) {
                error = UiText.StringResource(R.string.invalid_email).asString(requireContext())
                false
            } else {
                error = null
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}