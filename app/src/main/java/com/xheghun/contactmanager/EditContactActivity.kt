package com.xheghun.contactmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.xheghun.contactmanager.data.Contact

class EditContactActivity : AppCompatActivity() {

    private lateinit var firstnameTextLayout: TextInputLayout
    private lateinit var firstnameText: TextInputEditText
    private lateinit var lastnameText: TextInputEditText
    private lateinit var phoneNumText: TextInputEditText
    private lateinit var phoneNumLayout: TextInputLayout
    private lateinit var birthdayText: TextInputEditText
    private lateinit var addressText: TextInputEditText
    private lateinit var zipCodeText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        val menuItemClick = Toolbar.OnMenuItemClickListener { menuItem ->
            when(menuItem?.itemId) {
                R.id.save_btn -> {
                    if (firstnameText.text!!.isEmpty()) {
                        firstnameTextLayout.isErrorEnabled = true
                        firstnameTextLayout.error = "Firstname is required"
                    } else if (phoneNumText.text!!.isEmpty()) {
                        phoneNumLayout.isErrorEnabled = true
                        phoneNumLayout.error = "Phone number is required"
                    } else {
                        phoneNumLayout.isErrorEnabled = false; phoneNumLayout.error = ""
                        firstnameTextLayout.isErrorEnabled = false; firstnameTextLayout.error = ""
                        createNewContact()
                    }
                }
                R.id.delete_contact -> {finish()}
            }
            true
        }

        toolbar.setNavigationOnClickListener{finish()}
        toolbar.setOnMenuItemClickListener(menuItemClick)

        firstnameTextLayout = findViewById(R.id.firstname_layout)
        firstnameText = findViewById(R.id.firstname_text)
        lastnameText = findViewById(R.id.lastname_text)
        phoneNumLayout = findViewById(R.id.phone_num_layout)
        phoneNumText = findViewById(R.id.phone_num_text)
        birthdayText = findViewById(R.id.birthday_text)
        addressText = findViewById(R.id.address_text)
        zipCodeText = findViewById(R.id.zip_code_text)
    }

    private fun createNewContact() {
        val contact = Contact(firstnameText.text.toString(),lastnameText.text.toString(),phoneNumText.text.toString(),
            birthdayText.text.toString(),addressText.text.toString(),zipCodeText.text.toString())
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY,contact)
        setResult(Activity.RESULT_OK,replyIntent)
        finish()
    }

    companion object {
        const val EXTRA_REPLY = "com.xheghun.contact_manager.REPLY"
    }
}
