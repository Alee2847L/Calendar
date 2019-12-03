package com.example.calendaraplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

class Register_Activity : AppCompatActivity() {

    private lateinit var txtName:EditText
    private lateinit var txtApellido:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_)

        txtName=findViewById(R.id.txtname)
        txtApellido=findViewById(R.id.txtapellido)
        txtEmail=findViewById(R.id.txtEmail)
        txtPassword=findViewById(R.id.txtpassword)
        txtName=findViewById(R.id.txtname)

        progressBar= findViewById(R.id.progressBar)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbReference=database.reference.child("User")
    }

    fun register(view:View){
            createnewAcount()
    }
    fun log(view: View){
        startActivity(Intent(this, Login_Activity::class.java))
        this.finish()
    }

    private fun createnewAcount(){
        val name:String=txtName.text.toString()
        val apellido:String=txtApellido.text.toString()
        val email:String=txtEmail.text.toString()
        val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(apellido) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail(user)

                        val userDB=dbReference.child(user!!.uid)

                        userDB.child("Name").setValue(name)
                        userDB.child("Apellido").setValue(apellido)
                        action()
                    }
                }
        }
    }

    private fun action(){
        startActivity(Intent(this, Login_Activity::class.java))
        this.finish()
    }

    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                task ->
                if(task.isComplete){
                    Toast.makeText(this, "Email Enviado", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error al enviar correo", Toast.LENGTH_SHORT).show()
                }

            }
    }
}
