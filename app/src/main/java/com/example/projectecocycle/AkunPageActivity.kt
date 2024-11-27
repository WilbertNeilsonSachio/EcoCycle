package com.example.projectecocycle

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AkunPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var profilePicture: ImageView
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var logoutButton: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun_page)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Bind Views
        profilePicture = findViewById(R.id.profile_picture)
        usernameTextView = findViewById(R.id.username)
        emailTextView = findViewById(R.id.email)
        logoutButton = findViewById(R.id.logout_container)

        val user = auth.currentUser
        if (user != null) {
            loadUserProfile(user)
        }
        logoutButton.setOnClickListener {
            auth.signOut()
            // Redirect to Login Page
            val intent = Intent(this, LoginPageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_bottom)
        bottomNavigationView.selectedItemId = R.id.nav_akun
        // Handle menu item clicks
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_beranda -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_transaksi -> {
                    val intent = Intent(this, TransaksiPageActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_jual -> {
                    val intent = Intent(this, JualPageActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_bantuan -> {
                    val intent = Intent(this, BantuanPageActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_akun -> {
                    val intent = Intent(this, AkunPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
    private fun loadUserProfile(user: FirebaseUser) {
        // Set email
        emailTextView.text = user.email

        // Log the photo URL for debugging
        Log.d("AkunActivity", "Photo URL from FirebaseUser: ${user.photoUrl}")

        // Check if the user logged in with Google
        if (user.photoUrl != null) {
            // Use FirebaseUser's profile picture
            Glide.with(this)
                .load(user.photoUrl)
                .placeholder(R.drawable.icon_profile) // Default placeholder image
                .error(R.drawable.icon_profile) // Default image on error
                .circleCrop()
                .into(profilePicture)
        } else {
            // Attempt to fetch the Google profile picture URL via GoogleSignInAccount
            val account = GoogleSignIn.getLastSignedInAccount(this)
            if (account?.photoUrl != null) {
                Log.d("AkunActivity", "Photo URL from GoogleSignInAccount: ${account.photoUrl}")
                Glide.with(this)
                    .load(account.photoUrl)
                    .placeholder(R.drawable.icon_profile) // Default placeholder image
                    .error(R.drawable.icon_profile) // Default image on error
                    .circleCrop()
                    .into(profilePicture)
            } else {
                // Use default profile picture if no URL is available
                profilePicture.setImageResource(R.drawable.icon_profile) // Default profile icon
                Log.d("AkunActivity", "No profile picture available; using default.")
            }
        }
        // Set username
        usernameTextView.text = user.displayName ?: "Your Username" // Use FirebaseUser's displayName or fallback
    }
}