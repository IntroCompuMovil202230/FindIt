package com.ntn.findtit.ui.mychallenge

import android.os.Bundle
import com.ntn.findtit.ui.mychallenge.Add_Clue_itemFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ntn.findtit.R
import com.ntn.findtit.databinding.FragmentAddClueBinding
import com.ntn.findtit.databinding.FragmentAddClueItemBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Add_Clue_itemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add_Clue_itemFragment : Fragment() {

    private var _binding: FragmentAddClueItemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddClueItemBinding.inflate(inflater, container, false)

        binding.nextAddClueItem.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = Objetive_ChallengeFragment()
                replaceFragment(search)
            }
        })

        val arrow = binding.arrowBackAddClueItem
        arrow.setOnClickListener(object : View.OnClickListener {
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