package com.xheghun.contactmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xheghun.contactmanager.R
import com.xheghun.contactmanager._interface.ContactClickListener
import com.xheghun.contactmanager.data.Contact

class ContactListAdapter internal constructor(
    context: Context,
    val itemClickListener: ContactClickListener
) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>() //Cached copy of words

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactName: TextView = itemView.findViewById(R.id.contact_name)

        fun bind(contact: Contact,clickListener: ContactClickListener) {
            contactName.text = "${contact.firstname} ${contact.lastname}"
            itemView.setOnClickListener {
                clickListener.itemClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = inflater.inflate(R.layout.contact_item,parent,false)
        return ContactViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    internal fun setContacts(contact: List<Contact>) {
        this.contacts = contact
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position],itemClickListener)
    }
}
