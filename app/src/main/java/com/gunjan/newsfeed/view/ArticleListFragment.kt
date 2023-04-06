package com.gunjan.newsfeed.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunjan.newsfeed.databinding.FragmentArticleListBinding
import com.gunjan.newsfeed.view.adapter.NewsAdapter
import com.gunjan.newsfeed.viewmodel.CategoryEvent
import com.gunjan.newsfeed.viewmodel.SectionViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "ArticleListFragment"

class ArticleListFragment : Fragment() {
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!
    private val args: ArticleListFragmentArgs by navArgs()

    private val sectionViewModel: SectionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionViewModel.getNews(args.newsArticle, "67fc99e3-c8e9-4bfd-9ee7-e5620dbfc461")

        viewLifecycleOwner.lifecycleScope.launch {
            sectionViewModel.getCategory.collectLatest {
                when (it) {
                    is CategoryEvent.Loading -> {}
                    is CategoryEvent.Success -> {
                        val adapter = NewsAdapter(requireContext(), it.data)
                        binding.recyclerViewArticle.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.recyclerViewArticle.adapter = adapter
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