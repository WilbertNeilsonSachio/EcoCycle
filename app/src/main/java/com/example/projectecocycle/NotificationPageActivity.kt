package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotificationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_page)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_bottom)
        bottomNavigationView.selectedItemId = R.id.nav_transaksi
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
                    val intent = Intent(this, SalePageActivity::class.java)
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