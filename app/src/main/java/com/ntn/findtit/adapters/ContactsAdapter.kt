package com.ntn.findtit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ntn.findtit.R
import kotlin.random.Random

//TODO SOLO PRIMERA ENTREGA
data class Contact (val name:String, val message:String)

class ContactsAdapter(private val dataSet: List<Contact>):
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactName: TextView
        val previewMessage: TextView
        val unreadStatus: TextView

        init {
            contactName = view.findViewById(R.id.contact_name)
            previewMessage = view.findViewById(R.id.message_preview)
            unreadStatus = view.findViewById(R.id.unread_status)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_contact_chat, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.contactName.text = item.name
        viewHolder.previewMessage.text = item.message
        if (Random.nextInt(0,2) % 2 == 0){
            viewHolder.unreadStatus.visibility = View.VISIBLE
            viewHolder.unreadStatus.text = Random.nextInt(1,10).toString()
        }

    }
    override fun getItemCount() = dataSet.size





}