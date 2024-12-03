package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginPageActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var tvInputEmailAtauUsername: EditText
    private lateinit var tvTombolSelanjutnya: Button
    private lateinit var tvDaftarOption: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        // Bind views
        tvInputEmailAtauUsername = findViewById(R.id.tv_inputemailatauusername)
        tvTombolSelanjutnya = findViewById(R.id.tv_tombolselanjutnya)
        tvDaftarOption = findViewById(R.id.tv_daftaroption)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Your Web client ID from Firebase
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val tvGoogleSignIn: TextView = findViewById(R.id.tv_googleSignIn)
        tvGoogleSignIn.setOnClickListener {
            signInWithGoogle()
        }

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

    private fun signInWithGoogle() {
        googleSignInClient.signOut().addOnCompleteListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d("GoogleSignIn", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("GoogleSignIn", "Google sign in failed", e)
                Toast.makeText(this, "Google Sign-In failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("GoogleSignIn", "signInWithCredential:success")
                    val user = auth.currentUser
                    if (user != null) {
                        // Fetch profile picture from GoogleSignInAccount
                        val account = GoogleSignIn.getLastSignedInAccount(this)
                        val profilePictureUrl = account?.photoUrl?.toString()

                        // Log the retrieved photo URL for debugging
                        Log.d("GoogleSignIn", "Google Profile Picture URL: $profilePictureUrl")

                        // Redirect to HomePage (or next activity)
                        val intent = Intent(this, HomePageActivity::class.java).apply {
                            putExtra("profilePictureUrl", profilePictureUrl) // Pass photo URL
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e("GoogleSignIn", "FirebaseUser is null after sign-in")
                        Toast.makeText(this, "User data could not be retrieved!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.w("GoogleSignIn", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
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