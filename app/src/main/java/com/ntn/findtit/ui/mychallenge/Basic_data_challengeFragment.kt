package com.ntn.findtit.ui.mychallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ntn.findtit.R
import com.ntn.findtit.adapters.DesafioEditarAdapter
import com.ntn.findtit.databinding.FragmentBasicDataChallengeBinding
import com.ntn.findtit.databinding.FragmentMyChallengesBinding
import com.ntn.findtit.entity.Desafio


/**
 * A simple [Fragment] subclass.
 * Use the [Basic_data_challengeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Basic_data_challengeFragment : Fragment() {

    private var _binding: FragmentBasicDataChallengeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasicDataChallengeBinding.inflate(inflater, container, false)

        binding.nextCreateBasic.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = Objetive_ChallengeFragment()
                replaceFragment(search)
            }
        })

        val arrow = binding.arrowBackCreateBasic
        arrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fm: FragmentManager = activity!!.supportFragmentManager
                fm.popBackStack()
            }
        })

        val back = binding.backButtonBasic
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
        fragmentTrac.replace(R.id.nav_host_fragment_activity_principal,fragment,"create")
        fragmentTrac.addToBackStack("create")
        fragmentTrac.commit()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}