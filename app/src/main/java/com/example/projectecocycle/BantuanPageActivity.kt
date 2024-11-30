package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BantuanPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan_page)

        val notificon = findViewById<ImageView>(R.id.notificon)
        notificon.setOnClickListener {
            val intent = Intent(this, NotificationPageActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_bottom)
        bottomNavigationView.selectedItemId = R.id.nav_bantuan
        // Handle menu item clicks
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_beranda -> {
                    val intent = Intent(this, HomePageActivity::class.java)
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
}