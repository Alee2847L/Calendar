package com.example.calendaraplication

import android.net.Uri

object AppConstants {
    //val TAKE_PHOTO_REQUEST: Int = 2
    val PICK_PHOTO_REQUEST: Int = 1
    var fileUri: Uri? = null
    var file:MutableList<Uri?> = mutableListOf(fileUri)
    var pos : Int = 0
    val imgagenes=ArrayList<image>()
    var userEmail:String="Free Acount"
}