package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ConfirmationRegisterPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tvInputPassword: EditText
    private lateinit var tvLoginButton: Button
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_register_page)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Get the email passed from LoginPageActivity
        email = intent.getStringExtra("email")

        // Bind views
        tvInputPassword = findViewById(R.id.tv_inputsandi)
        tvLoginButton = findViewById(R.id.tv_konfirmasibtn)

        // Handle Login Button Click
        tvLoginButton.setOnClickListener {
            val password = tvInputPassword.text.toString().trim()

            if (password.isEmpty()) {
                tvInputPassword.error = "Enter your password"
                return@setOnClickListener
            }

            // Authenticate with Firebase Auth
            auth.signInWithEmailAndPassword(email!!, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Login successful
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        // Redirect to another activity, e.g., HomeActivity
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Login failed
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}