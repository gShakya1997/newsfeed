package com.gunjan.newsfeed.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.NavigationRedirection
import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.databinding.FragmentLoginBinding
import com.gunjan.newsfeed.viewmodel.Event
import com.gunjan.newsfeed.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            if (isEmpty(binding.textInputEditTextEmail) and isEmpty(binding.textInputEditTextPassword)) {
                userViewModel.login(
                    binding.textInputEditTextEmail.text.toString(),
                    binding.textInputEditTextPassword.text.toString()
                )
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.login.collectLatest {
                when (it) {
                    is Event.Loading -> {}
                    is Event.Success -> {
                        NavigationRedirection.navigateToFragment(
                            view,
                            R.id.action_loginFragment_to_dashboardActivity
                        )
                    }
                    is Event.Failure -> {
                        Toast.makeText(
                            requireContext(),
                            it.message.asString(requireContext()), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.btnBack.setOnClickListener { NavigationRedirection.navigateBack(it) }
    }

    private fun isEmpty(textInputEditText: TextInputEditText): Boolean {
        textInputEditText.apply {
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