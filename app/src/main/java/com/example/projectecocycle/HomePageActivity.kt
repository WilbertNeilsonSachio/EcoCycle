package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

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

        val plasticbtn = findViewById<ImageView>(R.id.plastikbtn)
        plasticbtn.setOnClickListener {
            val intent = Intent(this, DraftPlasticPageActivity::class.java)
            startActivity(intent)
        }

        val paperbtn = findViewById<ImageView>(R.id.kertaskardusbtn)
        paperbtn.setOnClickListener {
            val intent = Intent(this, DraftPaperPageActivity::class.java)
            startActivity(intent)
        }

        val glassbtn = findViewById<ImageView>(R.id.kacabtn)
        glassbtn.setOnClickListener {
            val intent = Intent(this, DraftGlassPageActivity::class.java)
            startActivity(intent)
        }

        val seeall = findViewById<TextView>(R.id.lihatsemua)
        seeall.setOnClickListener {
            val intent = Intent(this, DraftAllTrashPageActivity::class.java)
            startActivity(intent)
        }


        // Bind BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_bottom)
        bottomNavigationView.selectedItemId = R.id.nav_beranda

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