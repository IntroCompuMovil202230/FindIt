package com.ntn.findtit.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ntn.findtit.adapters.ChallengeSearchAdapter
import com.ntn.findtit.databinding.FragmentChallengeSearchBinding
import com.ntn.findtit.entity.Desafio

/**
 * A simple [Fragment] subclass.
 * Use the [ChallengeSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChallengeSearchFragment : Fragment() {

    private var _binding: FragmentChallengeSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChallengeSearchBinding.inflate(inflater, container, false)

        val arrow = binding.arrowBackSearch
            arrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fm: FragmentManager = activity!!.supportFragmentManager
                fm.popBackStack()
            }
        })


        val desafios = ArrayList<Desafio>()
        for(i in 0..25) {
            val de = Desafio("numero"+i, "na", i, i,5,"Media");
            desafios.add(de)
        }
        val d = ChallengeSearchAdapter(context, desafios);
        val list = binding.listaSearch
        list.adapter = d

        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}