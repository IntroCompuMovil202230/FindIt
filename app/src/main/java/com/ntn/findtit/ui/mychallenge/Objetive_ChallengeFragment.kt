package com.ntn.findtit.ui.mychallenge

import android.os.Bundle
import com.ntn.findtit.ui.mychallenge.Objetive_ChallengeFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ntn.findtit.R
import com.ntn.findtit.adapters.ChallengeSearchAdapter
import com.ntn.findtit.databinding.FragmentChallengeSearchBinding
import com.ntn.findtit.databinding.FragmentObjetiveChallengeBinding
import com.ntn.findtit.entity.Desafio

/**
 * A simple [Fragment] subclass.
 * Use the [Objetive_ChallengeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Objetive_ChallengeFragment : Fragment() {

    private var _binding: FragmentObjetiveChallengeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentObjetiveChallengeBinding.inflate(inflater, container, false)

        binding.nextObjective.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = AddClueFragment()
                replaceFragment(search)
            }
        })

        val arrow = binding.arrowBackObjective
        arrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fm: FragmentManager = activity!!.supportFragmentManager
                fm.popBackStack()
            }
        })

        val back = binding.backButtonObjective
        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fm: FragmentManager = activity!!.supportFragmentManager
                fm.popBackStack()
            }
        })

        return binding.root
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentTrac = getParentFragmentManager().beginTransaction()
        fragmentTrac.replace(R.id.nav_host_fragment_activity_principal,fragment,"objective")
        fragmentTrac.addToBackStack("objective")
        fragmentTrac.commit()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}