package com.example.ewss.ui.main.diagnosa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ewss.databinding.FragmentDiagnosaBinding

class DiagnosaFragment : Fragment() {

    private lateinit var diagnosaViewModel: DiagnosaViewModel
    private var _binding: FragmentDiagnosaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        diagnosaViewModel =
            ViewModelProvider(this).get(DiagnosaViewModel::class.java)

        _binding = FragmentDiagnosaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}