package com.example.ewss.ui.main.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ewss.databinding.FragmentAccountBinding
import com.example.ewss.ui.signin.SignInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {
    private val accountViewModel: AccountViewModel by viewModel()
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountViewModel.getDataUser().observe(requireActivity(), {
            with(binding) {
                name.text = it.name
                inputPhoneNumber.setText(it.phone)
                inputEmail.setText(it.email)
            }
        })
        binding.logout.setOnClickListener {
            accountViewModel.logout()
            startActivity(
                Intent(requireActivity(), SignInActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}