package com.xheghun.contactmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
define class to handle database

database columns
    firstname, lastname, phone number, birthday, address, zip code

an entity class defines a SQLite table
a ColumnInfo annotation specifies the name of the column in a table
if you want it to be different from the variable name
*/


@Entity(tableName = "contacts_table")
data class Contact(@ColumnInfo(name = "firstname") val firstname: String, @ColumnInfo(name = "lastname") val lastname: String,
                   @PrimaryKey @ColumnInfo(name = "phone_number") val phoneNumber: String, @ColumnInfo(name = "birthday") val birthday: String,
                   @ColumnInfo(name = "address") val address: String, @ColumnInfo(name ="zip_code") val zipCode: String)