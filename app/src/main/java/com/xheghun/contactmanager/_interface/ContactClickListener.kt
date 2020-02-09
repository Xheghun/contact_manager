package com.xheghun.contactmanager._interface

import com.xheghun.contactmanager.data.Contact

interface ContactClickListener {
    fun itemClick(contact: Contact)
}