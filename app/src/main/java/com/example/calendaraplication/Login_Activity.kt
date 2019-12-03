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
import kotlinx.android.synthetic.main.activity_login_.*

class Login_Activity : AppCompatActivity() {

    private lateinit var txtuser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)

        backCalendar.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

        txtuser=findViewById(R.id.txtEmail)
        txtPassword=findViewById(R.id.txtpassword)

        progressBar= findViewById(R.id.progressBar)

        auth=FirebaseAuth.getInstance()

    }
    fun log(view:View){
        loginUser()
    }
    fun reg(view: View){
        startActivity(Intent(this, Register_Activity::class.java))
        this.finish()
    }

    private fun loginUser(){
        val user:String=txtuser.text.toString()
        val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        action()
                        AppConstants.userEmail=user
                    }else{
                        Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
}
