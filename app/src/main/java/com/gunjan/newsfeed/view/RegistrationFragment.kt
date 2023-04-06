package com.gunjan.newsfeed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.core.utils.UiText
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
            if (isFullNameValid() and isPhoneValid() and isAddressValid() and isEmailValid()) {
                binding.apply {
                    val actionExploreToProductList =
                        RegistrationFragmentDirections.actionRegisterToSetPassword(
                            textInputEditTextFullName.text.toString(),
                            textInputEditTextPhone.text.toString(),
                            textInputEditTextAddress.text.toString(),
                            textInputEditTextEmail.text.toString()
                        )
                    binding.root.findNavController().navigate(actionExploreToProductList)
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