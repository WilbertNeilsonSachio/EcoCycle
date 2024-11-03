package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val nextButton = findViewById<Button>(R.id.tv_tombolselanjutnya)

        nextButton.setOnClickListener {
            val intent = Intent(this, ConfirmationRegisterPageActivity::class.java)
            startActivity(intent)
        }
    }
}