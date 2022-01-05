package com.example.ewss.ui.main.diagnosa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.core.data.Resource
import com.example.core.presentation.model.DiagnosaForm
import com.example.ewss.databinding.FragmentDiagnosaBinding
import com.example.ewss.ui.main.diagnosa.result.DiagnosaResultFragment
import com.google.android.material.textfield.TextInputLayout
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
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupListener() {
        with(binding){
            inputFullname.setAutoClearError(fullname)
            inputAddress.setAutoClearError(address)
            inputAge.setAutoClearError(age)
            inputNadi.setAutoClearError(nadi)
            inputPernafasan.setAutoClearError(pernafasan)
            inputSuhu.setAutoClearError(suhu)
            inputTekananDarah.setAutoClearError(tekananDarah)


           btnDiagnosa.setOnClickListener {
                if (isValidForm()){
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
                }else{
                    showErrorMessage()
                }
            }
        }
    }

    private fun isValidForm(): Boolean =
        binding.inputFullname.text.toString().isNotEmpty() &&
                binding.inputAddress.text.toString().isNotEmpty() &&
                binding.inputAge.text.toString().isNotEmpty() &&
                binding.inputNadi.text.toString().isNotEmpty() &&
                binding.inputPernafasan.text.toString().isNotEmpty() &&
                binding.inputSuhu.text.toString().isNotEmpty() &&
                binding.inputTekananDarah.text.toString().isNotEmpty()

    private fun formErrorHandler(viewInput: TextInputLayout, errorCondition: Boolean, errorMessage: String){
        if (errorCondition) viewInput.error = errorMessage
    }

    private fun showErrorMessage() {
        with(binding) {
            formErrorHandler(
                fullname,
                inputFullname.text.toString().isEmpty(),
                "Nama tidak boleh kosong"
            )
            formErrorHandler(
                address,
                inputAddress.text.toString().isEmpty(),
                "Alamat tidak boleh kosong"
            )
            formErrorHandler(
                age,
                inputAge.text.toString().isEmpty(),
                "Umur tidak boleh kosong"
            )
            formErrorHandler(
                nadi,
                inputNadi.text.toString().isEmpty(),
                "Denyut nadi tidak boleh kosong"
            )
            formErrorHandler(
                pernafasan,
                inputPernafasan.text.toString().isEmpty(),
                "Pernafasan tidak boleh kosong"
            )
            formErrorHandler(
                suhu,
                inputSuhu.text.toString().isEmpty(),
                "Suhu tidak boleh kosong"
            )
            formErrorHandler(
                tekananDarah,
                inputTekananDarah.text.toString().isEmpty(),
                "Tekanan darah tidak boleh kosong"
            )
        }
    }


    private fun EditText.setAutoClearError(viewInput: TextInputLayout){
        addTextChangedListener {
            if (it.toString().isNotEmpty() && viewInput.error != null) {
                viewInput.error = null
            }
        }
    }

    private fun clearForm(){
        with(binding){
            inputFullname.setText("")
            inputAge.setText("")
            inputAddress.setText("")
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
                denyutNadi = inputNadi.text.toString().toInt(),
                suhu = inputSuhu.text.toString().toDouble(),
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