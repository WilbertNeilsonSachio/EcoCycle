package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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

class LoginPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var tvInputEmail: EditText
    private lateinit var tvTombolSelanjutnya: Button
    private lateinit var tvGoogleSignIn: TextView
    private lateinit var tvDaftarOption: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Bind views
        tvInputEmail = findViewById(R.id.tv_inputemail)
        tvTombolSelanjutnya = findViewById(R.id.tv_tombolselanjutnya)
        tvGoogleSignIn = findViewById(R.id.tv_googleSignIn)
        tvDaftarOption = findViewById(R.id.tv_daftaroption)

        // Handle 'Selanjutnya' Button Click
        tvTombolSelanjutnya.setOnClickListener {
            val email = tvInputEmail.text.toString().trim()
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvInputEmail.error = "Enter a valid email"
                return@setOnClickListener
            }

            // Check if the email exists in Firestore
            firestore.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documents = task.result
                        if (!documents.isEmpty) {
                            // Email exists in Firestore, redirect to ConfirmationRegisterPageActivity
                            val intent = Intent(this, ConfirmationRegisterPageActivity::class.java)
                            intent.putExtra("email", email) // Pass email to the next activity
                            startActivity(intent)
                        } else {
                            // Email not found in Firestore
                            Toast.makeText(this, "Email not registered. Please register first.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Handle 'Google Sign-In' TextView Click
        tvGoogleSignIn.setOnClickListener {
            // TODO: Implement Google Sign-In (Optional)
        }

        // Handle 'Daftar' Button Click
        tvDaftarOption.setOnClickListener {
            val intent = Intent(this, RegisterPageActivity::class.java)
            startActivity(intent)
        }
    }
}