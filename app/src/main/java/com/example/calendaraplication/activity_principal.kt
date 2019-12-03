package com.example.calendaraplication


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_principal.*
import java.text.SimpleDateFormat
import java.util.*

class activity_principal : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    var storage: FirebaseStorage?=null
    var firestore:FirebaseFirestore?=null
    private lateinit var database: FirebaseDatabase
    var auth : FirebaseAuth?=null

    val mutableList: MutableList<String> = mutableListOf(AppConstants.fileUri.toString())
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        /*
        inicializar las variables de almacenamiento
        de FiREBASE
         */
        storage = FirebaseStorage.getInstance()
        //firestore= FirebaseFirestore.getInstance()



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
        //comming soon
        recycleView.setOnClickListener{ val Intent1 = Intent(this, content_Activity::class.java)
            startActivity(Intent1)
        }
        val recyclerView :RecyclerView=findViewById(R.id.recycleView)
        layoutManager=GridLayoutManager(this, 3)

        val adapter=Adapteriamge(this, AppConstants.imgagenes, object: ClickListener{
            override fun onClick(view: View, index: Int) {
                AppConstants.pos=index
                val intent=Intent(this@activity_principal,content_Activity::class.java)
                startActivity(intent)
                //Toast.makeText(applicationContext,"image",Toast.LENGTH_SHORT).show()
            }
        })
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
            AppConstants.file.add(AppConstants.fileUri)
            //--------------------------------------------------------------------
            //------SIBIR LA IMAGEN-----------------------
            //-------------------------------------------------------------------
            uploadImage()

            AppConstants.imgagenes.add(image(AppConstants.fileUri))
            //imgagenes.add(image(R.drawable.administration))

            //imgagenes.add(image(R.drawable.fnd))
            //for(element in imgagenes){



        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun insertInDataBase(){

        val file:String=AppConstants.fileUri.toString()

        if(!TextUtils.isEmpty(file)){

        }

    }

    private fun uploadImage(){
        /*
        crear nombre del archivo con fecha de subida
         */
        val timestamp= SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imagename= "IMAGE_"+ timestamp + "_.png"
        val storage=storage?.reference?.child("images")?.child(imagename)

        storage?.putFile(AppConstants.fileUri!!)?.continueWithTask{task: com.google.android.gms.tasks.Task<UploadTask.TaskSnapshot> ->
            return@continueWithTask storage.downloadUrl
        }?.addOnSuccessListener { uri ->
            val contentDTO=ContentDTO()

            //downloadURL to image
            contentDTO.imageUrl=uri.toString()

            //insertar el UID
            contentDTO.uid=auth?.currentUser?.uid

            //insertarr userID
            contentDTO.userId=auth?.currentUser?.email

            //tiempo de subida
            contentDTO.timestamp=System.currentTimeMillis()
            firestore?.collection("images")?.document()?.set(contentDTO)
            setResult(Activity.RESULT_OK)
        }
    }



}
