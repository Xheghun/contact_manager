package com.xheghun.contactmanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/*
    DAO means data access object, when creating a DOA you should either use an interface or
    an abstract class

    DAOs are used by room to generate a clean API for interacting with your database
*
* */

@Dao
interface ContactDao {
    //function to get all contacts from the table ordering them by their firstname
    @Query("SELECT * from contacts_table ORDER BY firstname ASC")
    fun getContacts(): LiveData<List<Contact>>

    //declare a function to insert new contact into database,
    //this function will replace the contact if it already exist
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewContact(contact: Contact)


    //function to delete all contacts in a database
    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContact()
}