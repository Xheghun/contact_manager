package com.xheghun.contactmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xheghun.contactmanager.adapter.ContactListAdapter
import com.xheghun.contactmanager.data.Contact
import com.xheghun.contactmanager.viewmodel.ContactViewModel

class ContactListActivity : AppCompatActivity() {
    private val newContactRequestCode = 1
    private val updateContaRequestCode = 2

    private lateinit var contactViewModel: ContactViewModel
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        val menuItemSelected = Toolbar.OnMenuItemClickListener {
            contactViewModel.delete()
            true
        }
        toolbar.setOnMenuItemClickListener(menuItemSelected)

        recyclerView = findViewById(R.id.recyclerView)
        val adapter = ContactListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        contactViewModel.getContacts.observe(this, Observer { contacts ->
            contacts?.let {adapter.setContacts(it)
        } })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newContactRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getSerializableExtra(EditContactActivity.EXTRA_REPLY)?.let {
                val contact = it as Contact
                //val contact = Contact("hi","k","m","ie","mdn", "820290")
                contactViewModel.insert(contact)
            }
        } else {
            Toast.makeText(applicationContext,
                "Contact not saved",
                Toast.LENGTH_SHORT).show()
        }
    }

    public fun toEdit(view: View) {
        startActivityForResult(Intent(this,EditContactActivity::class.java),newContactRequestCode)
    }
}
