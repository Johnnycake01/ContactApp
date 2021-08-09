package com.example.contacts.dataClass

import androidx.annotation.DrawableRes

data class ContactContent(@DrawableRes val image:Int, val name: String, val phone:String, val email:String)
