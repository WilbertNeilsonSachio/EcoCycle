package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DraftPlasticPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draft_plastic_page)

        val notificon = findViewById<ImageView>(R.id.notificon)
        notificon.setOnClickListener {
            val intent = Intent(this, NotificationPageActivity::class.java)
            startActivity(intent)
        }

        val tukarbtn = findViewById<Button>(R.id.tv_tukarbtn)
        tukarbtn.setOnClickListener {
            val intent = Intent(this, CoinPageActivity::class.java)
            startActivity(intent)
        }
    }
}