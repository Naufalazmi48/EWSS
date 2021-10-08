package com.example.ewss.ui.main.diagnosa.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.ewss.R
import com.example.ewss.databinding.FragmentDiagnosaResultBinding

class DiagnosaResultFragment : DialogFragment() {
    companion object {
        const val DATA_RESULT = "DATA_RESULT"
        const val DATA_MESSAGE = "DATA_MESSAGE"
    }

    private var _binding: FragmentDiagnosaResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiagnosaResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            diagnosaResult.text = getString(
                R.string.diagnosa_result, arguments?.getString(
                    DATA_RESULT
                )
            )
            keterangan.setText(arguments?.getString(DATA_MESSAGE))
        }
    }
}