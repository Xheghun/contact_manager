package com.xheghun.contactmanager

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.xheghun.contactmanager.data.Contact
import java.text.SimpleDateFormat
import java.util.*

class EditContactActivity : AppCompatActivity() {

    private lateinit var firstnameTextLayout: TextInputLayout
    private lateinit var firstnameText: TextInputEditText
    private lateinit var lastnameText: TextInputEditText
    private lateinit var phoneNumText: TextInputEditText
    private lateinit var phoneNumLayout: TextInputLayout
    private lateinit var birthdayText: EditText
    private lateinit var birthdayLayout: TextInputLayout
    private lateinit var addressText: TextInputEditText
    private lateinit var zipCodeText: TextInputEditText

    private lateinit var calender: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)


        firstnameTextLayout = findViewById(R.id.firstname_layout)
        firstnameText = findViewById(R.id.firstname_text)
        lastnameText = findViewById(R.id.lastname_text)
        phoneNumLayout = findViewById(R.id.phone_num_layout)
        phoneNumText = findViewById(R.id.phone_num_text)
        birthdayText = findViewById(R.id.birthday_text)
        addressText = findViewById(R.id.address_text)
        zipCodeText = findViewById(R.id.zip_code_text)


        calender = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { mView, year, month, day ->
            calender.set(Calendar.YEAR,year)
            calender.set(Calendar.MONTH,month)
            calender.set(Calendar.DAY_OF_MONTH,day)
            updateLabel()
        }


        birthdayText.setOnClickListener { View.OnClickListener {
            DatePickerDialog(this,date,calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)).show()
        } }


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
                        val data = intent.getStringExtra("operation")
                        if (data == "new_contact") {
                            createNewContact()
                        } else if (data == "edit_contact") {
                            editContact()
                        }
                    }
                }
                R.id.delete_contact -> {finish()}
            }
            true
        }

        toolbar.setNavigationOnClickListener{finish()}
        toolbar.setOnMenuItemClickListener(menuItemClick)


    }

    private fun editContact() {
        TODO("edit contact not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createNewContact() {
        val contact = Contact(firstnameText.text.toString(),lastnameText.text.toString(),phoneNumText.text.toString(),
            birthdayText.text.toString(),addressText.text.toString(),zipCodeText.text.toString())
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY_CREATE,contact)
        setResult(Activity.RESULT_OK,replyIntent)
        finish()
    }

    private fun updateLabel () {
        val format = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(format, Locale.US)

        birthdayText.setText(dateFormat.format(calender.time))
    }

    companion object {
        const val EXTRA_REPLY_CREATE = "com.xheghun.contact_manager.CREATE"
        const val EXTRA_REPLY_UPDATE = "com.xheghun.contact_manager.UPDATE"
    }
}
