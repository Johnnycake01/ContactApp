package com.example.contacts.ui


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.contacts.R

const val REQUEST_CALL = 1
class ContactProfile : AppCompatActivity() {
    private lateinit var phone:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //get values from intent
        val extras = intent.extras?.getInt("picture")
        val name_update = intent.getStringExtra("text")
        val phoneUpdate = intent.getStringExtra("phoneNumber")
        val emailImported = intent.getStringExtra("emailCode")

        //reference to view
        val image: ImageView = findViewById(R.id.image_second_layout)
        val text = findViewById<TextView>(R.id.second_activity_text)
         phone = findViewById(R.id.phone)
        val email = findViewById<TextView>(R.id.email_address)
        val backArrow = findViewById<View>(R.id.back_arrow)

        //set value to views
        if (extras != null){
            image.setImageResource(extras)
        }
        text.text = name_update
        phone.text = phoneUpdate
        email.text = emailImported



        backArrow.setOnClickListener {
            onBackPressed()
        }

    }
    fun callNumber(view: View){
        makeCall()
    }
    private fun makeCall(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)

        }else{
        callContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
               callContact()
            }else{
                Toast.makeText(this,"Contact Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }
    fun callContact(){
        val dail = "tel:${phone.text}"
        val intent = Intent(Intent.ACTION_CALL, Uri.parse(dail))
        startActivity(intent)
    }
}