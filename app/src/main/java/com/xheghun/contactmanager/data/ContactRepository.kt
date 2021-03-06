package com.xheghun.contactmanager.data

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ContactRepository(private val contactDao: ContactDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    val allContacts: LiveData<List<Contact>> = contactDao.getContacts()

    suspend fun insert(contact: Contact) {
        contactDao.createNewContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.update(contact)
    }

    suspend fun deleteAll() {
        contactDao.deleteAllContact()
    }
}