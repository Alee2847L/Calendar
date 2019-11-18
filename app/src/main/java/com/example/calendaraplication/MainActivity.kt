package com.example.calendaraplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //VARIABLES PARA INICIALIZAR EL CALENDARIO EN LA FECHA DEL SISTEMA
        val calendar= Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month= calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val y= year
        val m=month
        val d=day

        // VARIABLE TIPO MUTABLELIST PARA AÑADIR EVENTOS A UNA FECHA
        val mut : MutableList<String> = mutableListOf("3/11/2019")


        dateTv.setText(""+ d +"/"+ m +"/"+ y)

        calendarView.setOnDateChangeListener { view, year:Int, month:Int, dayOfMonth:Int -> dateTv.setText(""+ dayOfMonth +"/"+ month +"/"+ year)
            //EVALUA LOS ELEMENTOS EN LA LISTA SI EXISTE ALGUN ELEMENTO
            for(element in mut){
                Log.d("mutable", element)
                //LA LISTA EN LA PISISION ELEMENTO CONTIENE LA FECHA
                if(element.contains(dateTv.text)){
                    editText2.setText(element)
                }else{
                    editText2.setText(dateTv.text)
                }
            }
        }
        //SIGNO MAS DE AÑADIR
        imageView9.setOnClickListener{
            //EN EL SIGNO MAS DAMOS CLICK Y AÑADIMOS A LA LISTA EL EVENTO
            mut.add(editText2.text.toString())
            editText2.setText(mut.toString())
        }


        //BOTON ESCONDIDO LOGIN
        imageView.setOnClickListener{

            if(dateTv.text=="1/0/2020")
            {
                dateTv.setText("Correct password")

                 val Intento1 = Intent(this, activity_principal::class.java)

                 this.finish()
                    startActivity(Intento1)

            }
            else{
                //DATAPICKER DIALOG PARA LA VENTAMA EMERGENTE DEL CALENDARIO
                val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    dateTv.setText(""+ mDay +"/"+ mMonth +"/"+ mYear)
                }, year, month, day)
                dpd.show()}
        }


    }
}
