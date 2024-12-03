package com.example.projectecocycle

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
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

        val tukarbtn = findViewById<Button>(R.id.tv_tukarbtn)
        tukarbtn.setOnClickListener {
            val intent = Intent(this, CoinPageActivity::class.java)
            startActivity(intent)
        }

        val whatsappLayout = findViewById<LinearLayout>(R.id.whatsapp_layout)
        whatsappLayout.setOnClickListener {
            openWhatsApp("6281263992520")
        }

        val instagramLayout = findViewById<LinearLayout>(R.id.instagram_layout)
        instagramLayout.setOnClickListener {
            openInstagramProfile("hawryyy_30") // Replace with the Instagram username
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
    private fun openWhatsApp(number: String) {
        try {
            val url = "https://wa.me/$number"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the case when WhatsApp is not installed
        }
    }

    private fun openInstagramProfile(username: String) {
        try {
            // Instagram app URL
            val appUri = Uri.parse("http://instagram.com/_u/$username")
            val appIntent = Intent(Intent.ACTION_VIEW, appUri)
            appIntent.setPackage("com.instagram.android")

            // Fallback URL for browser
            val webUri = Uri.parse("http://instagram.com/$username")
            val webIntent = Intent(Intent.ACTION_VIEW, webUri)

            // Try to open the Instagram app
            if (appIntent.resolveActivity(packageManager) != null) {
                startActivity(appIntent)
            } else {
                startActivity(webIntent)
            }
        } catch (e: android.content.ActivityNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this, "Instagram or a browser is not installed.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show()
        }
    }
}