package com.example.titaeva_v_08

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.titaeva_v_08.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var login = ""
    private var password =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar= supportActionBar!!
        actionBar.title="Login"

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging..")
        progressDialog.setCanceledOnTouchOutside(false)
        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()

        binding.authorizationBtn.setOnClickListener {
            validateData()
    }
}

    private fun validateData() {
        login = binding.editTextLogin.text.toString().trim()
        password= binding.editTextPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(login).matches()){
            binding.editTextLogin.error = "invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            binding.editTextPassword.error ="Please enter password"
        }
        else{
            firebaselogin()
        }
    }

    private fun firebaselogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(login,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser=firebaseAuth.currentUser
                val email= firebaseUser!!.email
                Toast.makeText(this,"LoggedIn as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,PersonalAreaActivity::class.java))
                finish()
            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            startActivity(Intent(this, PersonalAreaActivity::class.java))
            finish()
        }
    }
    }
