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

    private lateinit var firestore: FirebaseFirestore
    private lateinit var tvInputEmailAtauUsername: EditText
    private lateinit var tvTombolSelanjutnya: Button
    private lateinit var tvDaftarOption: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Bind views
        tvInputEmailAtauUsername = findViewById(R.id.tv_inputemailatauusername)
        tvTombolSelanjutnya = findViewById(R.id.tv_tombolselanjutnya)
        tvDaftarOption = findViewById(R.id.tv_daftaroption)

        // Handle 'Selanjutnya' Button Click
        tvTombolSelanjutnya.setOnClickListener {
            val input = tvInputEmailAtauUsername.text.toString().trim()
            if (input.isEmpty()) {
                tvInputEmailAtauUsername.error = "Enter a username or email"
                return@setOnClickListener
            }

            // Determine if the input is an email
            if (Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                // Input is an email, proceed with email-based login
                handleEmailLogin(input)
            } else {
                // Input is a username, query Firestore to get associated email
                handleUsernameLogin(input)
            }
        }

        // Handle 'Daftar' Button Click
        tvDaftarOption.setOnClickListener {
            val intent = Intent(this, RegisterPageActivity::class.java)
            startActivity(intent)
        }
    }
    private fun handleEmailLogin(email: String) {
        firestore.collection("users")
            .whereEqualTo("email", email)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documents = task.result
                    if (!documents.isEmpty) {
                        // Email exists in Firestore, proceed to password page
                        val intent = Intent(this, ConfirmationRegisterPageActivity::class.java)
                        intent.putExtra("email", email) // Pass email to the next activity
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Email not registered. Please register first.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun handleUsernameLogin(username: String) {
        firestore.collection("users")
            .whereEqualTo("username", username)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documents = task.result
                    if (!documents.isEmpty) {
                        val email = documents.documents[0].getString("email")
                        // Proceed to password page with the email retrieved from username
                        val intent = Intent(this, ConfirmationRegisterPageActivity::class.java)
                        intent.putExtra("email", email) // Pass email to the next activity
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Username not found. Please register first.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}