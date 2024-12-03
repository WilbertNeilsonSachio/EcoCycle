package com.example.projectecocycle

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var tvInputNamaPengguna: EditText
    private lateinit var tvInputEmail: EditText
    private lateinit var tvInputSandi: EditText
    private lateinit var tvInputKonfirmasiSandi: EditText
    private lateinit var tvRegisterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        // Initialize FirebaseAuth and Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Bind views
        tvInputNamaPengguna = findViewById(R.id.tv_inputnamapengguna)
        tvInputEmail = findViewById(R.id.tv_inputemail)
        tvInputSandi = findViewById(R.id.tv_inputsandi)
        tvInputKonfirmasiSandi = findViewById(R.id.tv_inputkonfirmasisandi)
        tvRegisterBtn = findViewById(R.id.tv_registerbtn)

        // Handle Register Button Click
        tvRegisterBtn.setOnClickListener {
            val username = tvInputNamaPengguna.text.toString().trim()
            val email = tvInputEmail.text.toString().trim()
            val password = tvInputSandi.text.toString().trim()
            val confirmPassword = tvInputKonfirmasiSandi.text.toString().trim()

            // Validate input
            if (username.isEmpty()) {
                tvInputNamaPengguna.error = "Enter your username"
                return@setOnClickListener
            }
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvInputEmail.error = "Enter a valid email"
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                tvInputSandi.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }
            if (confirmPassword != password) {
                tvInputKonfirmasiSandi.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Register user in FirebaseAuth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        // Save user data to Firestore
                        val userData = hashMapOf(
                            "username" to username,
                            "email" to email,
                            "userId" to userId
                        )

                        firestore.collection("users").document(userId!!)
                            .set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                                finish() // Close RegisterPageActivity
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed to save user data: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}