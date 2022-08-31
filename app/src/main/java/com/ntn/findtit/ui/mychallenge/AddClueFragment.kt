package com.ntn.findtit.ui.mychallenge

import android.os.Bundle
import com.ntn.findtit.ui.mychallenge.AddClueFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ntn.findtit.R
import com.ntn.findtit.SeeChallengeActivity
import com.ntn.findtit.databinding.FragmentAddClueBinding
import com.ntn.findtit.databinding.FragmentObjetiveChallengeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AddClueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddClueFragment : Fragment() {

    private var _binding: FragmentAddClueBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddClueBinding.inflate(inflater, container, false)

        binding.buttonAddClue.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = Add_Clue_itemFragment()
                replaceFragment(search)
            }
        })

        binding.nextAddClue.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = PreviewActivityFragment()
                replaceFragment(search)
            }
        })

        val arrow = binding.arrowBackAddClue
        arrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fm: FragmentManager = activity!!.supportFragmentManager
                fm.popBackStack()
            }
        })

        val back = binding.backButtonAddClue
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
        fragmentTrac.replace(R.id.nav_host_fragment_activity_principal,fragment,"addclue")
        fragmentTrac.addToBackStack("addclue")
        fragmentTrac.commit()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}