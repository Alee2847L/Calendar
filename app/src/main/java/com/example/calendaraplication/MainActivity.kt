package com.example.calendaraplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_principal.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //val mut : MutableList<String> = mutableListOf(dateTv.toString())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calendar= Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month= calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val y= year
        val m=month
        val d=day

        val mut : MutableList<String> = mutableListOf("3/11/2019 empleo")

        dateTv.setText(""+ d +"/"+ m +"/"+ y)

        calendarView.setOnDateChangeListener { view, year:Int, month:Int, dayOfMonth:Int -> editText2.setText(""+ dayOfMonth +"/"+ month +"/"+ year)
            if(mut.contains(editText2.toString())){
                editText2.setText(mut.contains(editText2.toString()).toString())
            }
          //  mut.add(dateTv.toString())
           // editText2.setText(mut.toString())
        }

        imageView9.setOnClickListener{
            mut.add(editText2.toString())
        }



        imageView.setOnClickListener{

            if(dateTv.text=="1/0/2020")
            {
                dateTv.setText("Correct password")

                 val Intento1 = Intent(this, activity_principal::class.java)

                 this.finish()
                    startActivity(Intento1)

            }
            else{
                val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    dateTv.setText(""+ mDay +"/"+ mMonth +"/"+ mYear)
                }, year, month, day)
                dpd.show()}
        }


    }
}
