package com.ntn.findtit.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ntn.findtit.databinding.FragmentAccountBinding

class AccountFragment :Fragment() {
    private var _binding: FragmentAccountBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAccount
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}