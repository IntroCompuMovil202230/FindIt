package com.ntn.findtit.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntn.findtit.adapters.Contact
import com.ntn.findtit.adapters.ContactsAdapter
import com.ntn.findtit.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        initialize()
        return binding.root
    }
    private fun initialize(){
        //contact recicle view
        val recycle = binding.contactView
        recycle.layoutManager = LinearLayoutManager(context)
        val data = ArrayList<Contact>()
        for (i in 1..20) {
            data.add(Contact("name$i", "Super message preview no. $i"))

        }
        recycle.adapter = ContactsAdapter(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}