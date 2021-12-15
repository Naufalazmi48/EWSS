package com.example.ewss.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.core.data.Resource
import com.example.core.domain.model.StatisticPatient
import com.example.ewss.R
import com.example.ewss.databinding.FragmentHomeBinding
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import de.hdodenhof.circleimageview.CircleImageView
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by inject()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showGreeting(true)
        viewModel.statistic.observe(viewLifecycleOwner, this::statisticObserver)
        viewModel.getStatistic()
    }

    private fun showGreeting(isShow: Boolean) {
        activity?.findViewById<CircleImageView>(R.id.avatar)?.isVisible = isShow
        activity?.findViewById<TextView>(R.id.greeting_admin)?.isVisible = isShow
        activity?.findViewById<TextView>(R.id.greeting)?.isVisible = isShow
    }

    private fun statisticObserver(resource: Resource<StatisticPatient>) {
       with(binding) {
           when(resource){
               is Resource.Error -> {
                   loading.isVisible = false
                   Toast.makeText(
                       requireContext(),
                       "Gagal load statistik",
                       Toast.LENGTH_SHORT
                   ).show()
               }
               is Resource.Loading -> loading.isVisible = true
               is Resource.Success -> {
                   loading.isVisible = false
                   statisticPatient.root.isVisible = true
                   resource.data?.let { setData(it) }
               }
           }
       }
    }

    private fun setData(statisticPatient: StatisticPatient) {

        val maxValue = statisticPatient.total
        val percentageStabil = (statisticPatient.stabil * 100) / maxValue
        val percentageWarning = (statisticPatient.hatiHati * 100) / maxValue
        val percentageAlert = (statisticPatient.waspada * 100) / maxValue
        val percentageDanger = (statisticPatient.berbahaya * 100) / maxValue

        with(binding.statisticPatient){

            stabilText.text = requireContext().getString(R.string.stabil,percentageStabil.toString())
            warningText.text = requireContext().getString(R.string.hati_hati,percentageWarning.toString())
            alertText.text = requireContext().getString(R.string.waspada,percentageAlert.toString())
            dangerText.text = requireContext().getString(R.string.berbahaya,percentageDanger.toString())

            val entries = arrayListOf(
                PieEntry(percentageStabil.toFloat()),
                PieEntry(percentageWarning.toFloat()),
                PieEntry(percentageAlert.toFloat()),
                PieEntry(percentageDanger.toFloat()),
            )

            val colors = arrayListOf(
                ContextCompat.getColor(requireContext(), R.color.green),
                ContextCompat.getColor(requireContext(), R.color.yellow),
                ContextCompat.getColor(requireContext(), R.color.orange),
                ContextCompat.getColor(requireContext(), R.color.red)
            )

            val pieData = PieData(PieDataSet(entries, "Statistik Pasien").apply {
                setColors(colors)
            }).apply {
                setDrawValues(false)
            }

            piechart.apply {
                data = pieData
                description = Description().apply { text = "" }
                legend.isEnabled = false
                invalidate()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showGreeting(false)
        _binding = null
    }
}