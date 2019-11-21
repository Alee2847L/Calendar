package com.example.calendaraplication


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_principal.*
import kotlin.collections.ArrayList

class activity_principal : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    val imgagenes=ArrayList<image>()

    val mutableList: MutableList<String> = mutableListOf(AppConstants.fileUri.toString())
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        //-----CLICK IN LOG OUT----------
        imageView2.setOnClickListener{ val Intento1 =Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(Intento1)
        }
        //------CLICK ADD IMAGE---------------
        imageView4.setOnClickListener{
            selectImageInAlbum()
        }
        //---------FULL SCREEN-------------------
        LinearLayout.setOnClickListener{ val Intent1 = Intent(this, content_Activity::class.java)
            startActivity(Intent1)
        }//comming soon
        recycleView.setOnClickListener{ val Intent1 = Intent(this, content_Activity::class.java)
            startActivity(Intent1)
        }
        val recyclerView :RecyclerView=findViewById(R.id.recycleView)
        layoutManager=LinearLayoutManager(this)

        val adapter=Adapteriamge(this, imgagenes)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()

    }


    private fun selectImageInAlbum() {
        //https://www.youtube.com/watch?v=lApKSHL15Es
        //get image from gallery
        val pickImageIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        //al obtener el resultado, lo almacenamos en una variable global
        //la cual esta dentro de AppConstants(KOTLIN OBJECTS)
        startActivityForResult(pickImageIntent, AppConstants.PICK_PHOTO_REQUEST)
    }
    //PARA EL SEGUNDO PARCIAL SE IMPLEMENTARA LA FUNCION TOMAR FOTO
    //---------------------------------------------------------------------
    //--------------------------------------------------------------------

    //FUNCION SOBRE CARGADA PARA EVALUAR EL RESULTADO
    //SI SE HA SELECCIONADO TOMAR FOTO O ELEGIRLA DE LA GALERIA
    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.PICK_PHOTO_REQUEST
        ) {
            //photo from gallery
            //FILEURI ES UNA VARIABLE TIPO URI EN LA CUAL SE ALMACENA EL URL DE LA IMAGEN ADQUIRIDA
            AppConstants.fileUri = data?.data
            mutableList.add(AppConstants.fileUri.toString())
            //--------------------------------------------------------------------
            //------USE RECYCLE VIEW IN ACTIVITY PRINCIPAL-----------------------
            //-------------------------------------------------------------------

            //EL  SIGUIENTE ERROR SE ORINA EN EL MOMENTO DE ARREGLAR EL RECICLE LAYOUT
            //EN DARLE SU DIRECCION Y ALINEAMIENTO
            /////////recyclerView.layoutManager=LinearLayoutManager(this, android.widget.LinearLayout.VERTICAL,false)

            imgagenes.add(image(AppConstants.fileUri))
            //imgagenes.add(image(R.drawable.administration))

            //imgagenes.add(image(R.drawable.fnd))
            //for(element in imgagenes){



        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
