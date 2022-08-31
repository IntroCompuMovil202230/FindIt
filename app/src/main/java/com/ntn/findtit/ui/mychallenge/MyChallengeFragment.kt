package com.ntn.findtit.ui.mychallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ntn.findtit.R
import com.ntn.findtit.adapters.DesafioEditarAdapter
import com.ntn.findtit.databinding.FragmentMyChallengesBinding
import com.ntn.findtit.entity.Desafio
import com.ntn.findtit.ui.challenge.ChallengeSearchFragment

class MyChallengeFragment : Fragment() {

    private var _binding: FragmentMyChallengesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyChallengesBinding.inflate(inflater, container, false)

        binding.createChallengeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = Basic_data_challengeFragment()
                replaceFragment(search)
            }
        })

        val desafios = ArrayList<Desafio>()
        for(i in 0..25) {
            val de = Desafio("numero"+i, "na", i, i,5);
            desafios.add(de);
        }
        val d = DesafioEditarAdapter(context, desafios);
        val list = binding.lista
        list.adapter = d

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