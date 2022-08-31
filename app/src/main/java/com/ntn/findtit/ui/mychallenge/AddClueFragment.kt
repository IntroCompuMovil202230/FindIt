package com.ntn.findtit.ui.mychallenge

import android.content.Intent
import android.os.Bundle
import com.ntn.findtit.ui.mychallenge.AddClueFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ntn.findtit.PreviewChallengeActivity
import com.ntn.findtit.R
import com.ntn.findtit.databinding.FragmentAddClueBinding

class AddClueFragment : Fragment() {

    private var _binding: FragmentAddClueBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddClueBinding.inflate(inflater, container, false)

        binding.buttonAddClue.setOnClickListener {
            val search = Add_Clue_itemFragment()
            replaceFragment(search)
        }

        binding.nextAddClue.setOnClickListener {
            PreviewChallengeActivity()
            val intent = Intent(context, PreviewChallengeActivity::class.java)
            startActivity(intent)
        }

        val arrow = binding.arrowBackAddClue
        arrow.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack()
        }

        val back = binding.backButtonAddClue
        back.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack()
        }

        return binding.root
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentTrac = parentFragmentManager.beginTransaction()
        fragmentTrac.replace(R.id.nav_host_fragment_activity_principal,fragment,"addclue")
        fragmentTrac.addToBackStack("addclue")
        fragmentTrac.commit()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}