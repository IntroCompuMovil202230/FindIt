
package com.ntn.findtit.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ntn.findtit.R
import com.ntn.findtit.databinding.FragmentChallangesBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallangesBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ChallengeViewModel::class.java)

        _binding = FragmentChallangesBinding.inflate(inflater, container, false)

        initialize()



        binding.cardTest.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val search = ChallengeSearchFragment()
                replaceFragment(search)
            }
        })
        return binding.root
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentTrac = getParentFragmentManager().beginTransaction()
        fragmentTrac.replace(R.id.nav_host_fragment_activity_principal,fragment,"search")
        fragmentTrac.addToBackStack("search")
        fragmentTrac.commit()
    }

    private fun initialize(){
        //carousel
        val images = binding.carousel
        images.setData(
            mutableListOf(
                CarouselItem(R.drawable.image0),
                CarouselItem(R.drawable.image2),
                CarouselItem(R.drawable.image1),
                CarouselItem(R.drawable.image3),
                CarouselItem(R.drawable.image4)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}