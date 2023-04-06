package com.gunjan.newsfeed.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.databinding.FragmentSetPasswordBinding

private const val TAG = "SetPasswordFragment"

class SetPasswordFragment : Fragment() {
    private var _binding: FragmentSetPasswordBinding? = null
    private val binding get() = _binding!!
    private val args: SetPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSetPasswordBinding.inflate(inflater, container, false)

        Log.e(TAG, "Fullname: ${args.fullName}")
        Log.e(TAG, "PhoneNo: ${args.phoneNumber}")
        Log.e(TAG, "Address: ${args.address}")
        Log.e(TAG, "Email: ${args.email}")

        binding.btnRegister.setOnClickListener { v ->
            if (isPasswordValid() and isConfirmPasswordValid()) {
                binding.apply {
                    Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT)
                        .show()
                    NavigationRedirection.navigateToFragment(
                        v,
                        R.id.action_setPasswordFragment_to_getStartedFragment
                    )
                }
            }
        }

        binding.btnBack.setOnClickListener { NavigationRedirection.navigateBack(it) }

        return binding.root
    }

    private fun isConfirmPasswordValid(): Boolean {
        binding.textInputEditTextConfirmPassword.apply {
            return if (text.isNullOrBlank()) {
                error = UiText.StringResource(R.string.required).asString(requireContext())
                false
            }else if (text.toString() != binding.textInputEditTextPassword.text.toString()
                    .trim()
            ) {
                error = UiText.StringResource(R.string.mismatch_password).asString(requireContext())
                false
            }  else {
                error = null
                true
            }
        }
    }

    private fun isPasswordValid(): Boolean {
        binding.textInputEditTextPassword.apply {
            return if (text.isNullOrBlank()) {
                error = UiText.StringResource(R.string.required).asString(requireContext())
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