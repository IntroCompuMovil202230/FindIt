package com.ntn.findtit.ui.challenge

import android.os.Bundle
import com.ntn.findtit.ui.challenge.MapFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ntn.findtit.R
import com.ntn.findtit.adapters.NotificationAdapter
import com.ntn.findtit.databinding.FragmentMapBinding
import com.ntn.findtit.databinding.FragmentPlayBinding
import com.ntn.findtit.entity.Notification

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        val arrow = binding.arrowBackPlay
        arrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fm: FragmentManager = activity!!.supportFragmentManager
                fm.popBackStack()
            }
        })

        binding.mapTermButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = PlayFragment()
                replaceFragment(search)
            }
        })

        val notificaciones = ArrayList<Notification>()

        for(i in 0..25) {
            val de = Notification("titulo "+i, "na"+i)
            notificaciones.add(de)
        }
        val d = NotificationAdapter(context, notificaciones)

/*
        val list = binding.listNotifications
        list.adapter = d
*/
        return binding.root
    }
    private fun replaceFragment(fragment:Fragment){
        val fragmentTrac = getParentFragmentManager().beginTransaction()
        fragmentTrac.replace(R.id.nav_host_fragment_activity_principal,fragment,"play")
        fragmentTrac.addToBackStack("play")
        fragmentTrac.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}