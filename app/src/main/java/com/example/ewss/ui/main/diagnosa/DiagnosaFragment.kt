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
            diagnosaViewModel.diagnosa(getDiagnosaForm()).observe(requireActivity(), {
                when (it) {
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
                        resultDialog.show(
                            childFragmentManager,
                            DiagnosaResultFragment::javaClass.name
                        )
                        clearForm()
                    }
                }
            })
        }
    }

    private fun clearForm(){
        with(binding){
            inputFullname.setText("")
            inputAge.setText("")
            inputAddress.setText("")
            kesadaranAutocomplete.setText("")
            inputSuhu.setText("")
            inputTekananDarah.setText("")
            inputPernafasan.setText("")
            inputNadi.setText("")
        }
    }

    private fun getDiagnosaForm(): DiagnosaForm {
        with(binding) {
            return DiagnosaForm(
                fullname = inputFullname.text.toString(),
                address = inputAddress.text.toString(),
                age = inputAge.text.toString().toInt(),
                kesadaran = kesadaranAutocomplete.text.toString(),
                denyutNadi = inputNadi.text.toString().toInt(),
                suhu = inputSuhu.text.toString().toInt(),
                pernafasan = inputPernafasan.text.toString().toInt(),
                tekananDarah = inputTekananDarah.text.toString().toInt()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}