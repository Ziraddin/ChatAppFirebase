package com.example.chatappfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatappfirebase.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest


class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

    }

    fun register(view: View) {
        val email = binding.editTextEmailAddress.text.toString().trim()
        val password = binding.editTextPassword.editText.toString().trim()
        val newDisplayName = binding.editTextDisplayName.text.toString().trim()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = newDisplayName
            }
            user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                if (updateTask.isSuccessful) {
                    Log.d("TAG", "User profile updated.")
                }
            }
            if (task.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener() { exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun goToLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}