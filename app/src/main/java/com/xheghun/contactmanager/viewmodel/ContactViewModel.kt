package com.xheghun.contactmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.xheghun.contactmanager.data.Contact
import com.xheghun.contactmanager.data.ContactRepository
import com.xheghun.contactmanager.data.ContactRoomDatabase
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class ContactViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ContactRepository
    //LiveData updates the contact when they change
    val getContacts: LiveData<List<Contact>>

    init {
        val contactDao = ContactRoomDatabase.getDatabase(application,viewModelScope).contactDao()
        repository = ContactRepository(contactDao)
        getContacts = repository.allContacts
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */

    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete() {
        repository
    }
}