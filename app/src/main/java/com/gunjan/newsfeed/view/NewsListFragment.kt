package com.gunjan.newsfeed.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunjan.newsfeed.databinding.FragmentNewsListBinding
import com.gunjan.newsfeed.view.adapter.NewsAdapter
import com.gunjan.newsfeed.viewmodel.CategoryEvent
import com.gunjan.newsfeed.viewmodel.SectionViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "NewsListFragment"

class NewsListFragment : Fragment() {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private val sectionViewModel: SectionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionViewModel.getNews("news", "67fc99e3-c8e9-4bfd-9ee7-e5620dbfc461")

        viewLifecycleOwner.lifecycleScope.launch {
            sectionViewModel.getCategory.collectLatest {
                when (it) {
                    is CategoryEvent.Loading -> {}
                    is CategoryEvent.Success -> {
                        val adapter = NewsAdapter(requireContext(), it.data)
                        binding.recyclerViewNews.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.recyclerViewNews.adapter = adapter
                    }
                    is CategoryEvent.Failure -> Log.e(
                        TAG,
                        "onViewCreated: ${it.title} and ${it.message}"
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}