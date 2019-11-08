package com.example.calendaraplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_principal.*
import java.util.*

class activity_principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        imageView2.setOnClickListener{ val Intento1 =Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(Intento1)
        }

        imageView4.setOnClickListener{
            selectImageInAlbum()
        }



    }


    private fun selectImageInAlbum() { val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null)
        {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
            val intent = Intent()
                intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), imageView3);
        }
    }
    private fun takePhoto() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null)
        {
            startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
        }
    }
    companion object { private val REQUEST_TAKE_PHOTO = 0 ; private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1 }



}
