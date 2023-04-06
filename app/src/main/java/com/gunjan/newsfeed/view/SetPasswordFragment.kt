package com.gunjan.newsfeed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.databinding.FragmentSetPasswordBinding
import com.gunjan.newsfeed.model.database.Users
import com.gunjan.newsfeed.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class SetPasswordFragment : Fragment() {
    private var _binding: FragmentSetPasswordBinding? = null
    private val binding get() = _binding!!
    private val args: SetPasswordFragmentArgs by navArgs()

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSetPasswordBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener {
            if (isPasswordValid() and isConfirmPasswordValid()) {
                val user = Users(
                    email = args.email,
                    fullName = args.fullName,
                    phone = args.phoneNumber,
                    address = args.address,
                    password = binding.textInputEditTextPassword.text.toString()
                )
                userViewModel.registerUser(user)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.registerUser.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        binding.apply {
                            Toast.makeText(
                                requireContext(),
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            NavigationRedirection.navigateToFragment(
                                binding.root,
                                R.id.action_setPasswordFragment_to_getStartedFragment
                            )
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.message!!.asString(requireContext()), Toast.LENGTH_SHORT
                        ).show()
                    }
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
            } else if (text.toString() != binding.textInputEditTextPassword.text.toString()
                    .trim()
            ) {
                error = UiText.StringResource(R.string.mismatch_password).asString(requireContext())
                false
            } else {
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