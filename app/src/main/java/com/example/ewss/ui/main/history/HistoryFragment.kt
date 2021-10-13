package com.example.ewss.ui.main.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.ewss.databinding.FragmentHistoryBinding
import com.example.ewss.ui.main.history.adapter.HistoryAdapter
import com.example.ewss.ui.main.history.detail.DetailDiagnosaActivity
import org.koin.android.ext.android.inject

class HistoryFragment : Fragment() {

    private val historyViewModel: HistoryViewModel by inject()
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObserver()
        setupListener()

        historyViewModel.getHistoryDiagnosa()
    }

    private fun setupAdapter() {

        historyAdapter = HistoryAdapter(requireContext())

        historyAdapter.onItemClick = {
            startActivity(
                Intent(requireContext(), DetailDiagnosaActivity::class.java)
                    .putExtra(DetailDiagnosaActivity.GET_DETAIL_DIAGNOSA, it)
            )
        }

        with(binding) {
            rvHistoryDiagnosa.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvHistoryDiagnosa.adapter = historyAdapter
        }
    }

    private fun setupListener() {
        with(binding) {
            refresh.setOnRefreshListener {
                historyViewModel.getHistoryDiagnosa()
            }

            searchPatient.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0.isNullOrEmpty()) historyViewModel.getHistoryDiagnosa()
                    else historyAdapter.filter.filter(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0.isNullOrEmpty()) historyViewModel.getHistoryDiagnosa()
                    else historyAdapter.filter.filter(p0)
                    return true
                }

            })
        }
    }

    private fun setupObserver() {
        historyViewModel.historyDiagnosa.observe(requireActivity(), {
            when (it) {
                is Resource.Error -> {
                    with(binding) {
                        if (refresh.isRefreshing) refresh.isRefreshing = false
                        else loading.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Failed to get data history diagnosa",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Resource.Loading -> {
                    with(binding) {
                        if (!refresh.isRefreshing) binding.loading.visibility = View.VISIBLE
                    }
                }
                is Resource.Success -> {
                    with(binding) {
                        if (refresh.isRefreshing) refresh.isRefreshing = false
                        else loading.visibility = View.GONE

                        historyAdapter.setData(it.data ?: emptyList())
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}