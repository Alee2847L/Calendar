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

    val mutableList: MutableList<String> = mutableListOf(dateTv.toString())
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

        dateTv.setText(""+ d +"/"+ m +"/"+ y)

        calendarView.setOnDateChangeListener { view, year:Int, month:Int, dayOfMonth:Int -> dateTv.setText(""+ dayOfMonth +"/"+ month +"/"+ year) }
        mutableList.add(dateTv.toString())
        editText2.setText(mutableList.toString())
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
