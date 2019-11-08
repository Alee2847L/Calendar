package com.example.calendaraplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }




}
