package com.example.ewss.ui.main.diagnosa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.core.data.Resource
import com.example.core.presentation.model.DiagnosaForm
import com.example.ewss.databinding.FragmentDiagnosaBinding
import com.example.ewss.ui.main.diagnosa.result.DiagnosaResultFragment
import com.example.ewss.utils.StatusPatient.setKesadaranAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiagnosaFragment : Fragment() {

    private val diagnosaViewModel: DiagnosaViewModel by viewModel()
    private var _binding: FragmentDiagnosaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiagnosaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupListener()
        setupAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupAdapter() {
        requireContext().setKesadaranAdapter(binding.kesadaranAutocomplete)
    }

    private fun setupListener() {
        binding.btnDiagnosa.setOnClickListener {
            // Data Dummy
            val dummy = DiagnosaForm(
                fullname = "Lalu Naufal Azmi",
                address = "Karang Pule",
                age = 21,
                kesadaran = "Apatis",
                pernafasan = 100,
                denyutNadi = 100,
                tekananDarah = 120,
                suhu = 20
            )

            diagnosaViewModel.diagnosa(dummy).observe(requireActivity(), {
                when(it) {
                    is Resource.Error -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> binding.loading.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.loading.visibility = View.GONE
                        val resultDialog = DiagnosaResultFragment()
                        resultDialog.arguments = Bundle().apply {
                            putString(DiagnosaResultFragment.DATA_RESULT, it.data?.result)
                            putString(DiagnosaResultFragment.DATA_MESSAGE, it.data?.detailResult)
                        }
                        resultDialog.show(childFragmentManager, DiagnosaResultFragment::javaClass.name)
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}