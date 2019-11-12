package com.example.calendaraplication

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Layout
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toIcon
import androidx.core.net.toFile
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_principal.*
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

class activity_principal : AppCompatActivity() {

    val mutableList: MutableList<String> = mutableListOf(AppConstants.fileUri.toString())
    var lista = ArrayList<String>()
    var arrayList= ArrayList<String>()
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

        imageView5.setOnClickListener{
            takePhoto()
        }


    }


    private fun selectImageInAlbum() {

        val pickImageIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(pickImageIntent, AppConstants.PICK_PHOTO_REQUEST)
    }
    private fun takePhoto() {
       var i=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(i, 123)


    }

    fun askCameraPermission(){
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {/* ... */
                    if(report.areAllPermissionsGranted()){
                        //once permissions are granted, launch the camera
                        takePhoto()
                    }else{
                        Toast.makeText(this@activity_principal, "All permissions need to be granted to take photo", Toast.LENGTH_LONG).show()
                    }
                }

                @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {/* ... */
                    //show alert dialog with permission options
                    AlertDialog.Builder(this@activity_principal)
                        .setTitle(
                            "Permissions Error!")
                        .setMessage(
                            "Please allow permissions to take photo with camera")
                        .setNegativeButton(
                            android.R.string.cancel,
                            { dialog, _ ->
                                dialog.dismiss()
                                token?.cancelPermissionRequest()
                            })
                        .setPositiveButton(android.R.string.ok,
                            { dialog, _ ->
                                dialog.dismiss()
                                token?.continuePermissionRequest()
                            })
                        .setOnDismissListener({
                            token?.cancelPermissionRequest() })
                        .show()
                }

            }).check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.TAKE_PHOTO_REQUEST
        ) {
            //photo from camera
            //display the photo on the imageview
            imageView3.setImageURI(AppConstants.fileUri)
        } else if (resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.PICK_PHOTO_REQUEST
        ) {
            //photo from gallery
            AppConstants.fileUri = data?.data
            imageView3.setImageURI(AppConstants.fileUri)
            lista.add(AppConstants.fileUri.toString())
            mutableList.add(AppConstants.fileUri.toString())
            //--------------------------------------------------------------------
            //-------------------------------------------------------------------
            val recyclerView :RecyclerView=findViewById(R.id.recycleView)
            recyclerView.layoutManager=LinearLayoutManager(this, android.widget.LinearLayout.VERTICAL,false)
            val imgagenes=ArrayList<image>()


            imgagenes.add(image(mutableList.last()))

            val adapter=Adapteriamge(imgagenes)
            recyclerView.adapter=adapter
            //--------------------------------------------------------------------
            //-------------------------------------------------------------------
            textView.setText(mutableList[1])
            editText.setText(mutableList.toString())
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
