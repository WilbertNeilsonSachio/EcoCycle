package com.example.projectecocycle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectecocycle.dataclass.TrashItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class DraftAllTrashPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draft_all_trash_page)

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

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_bottom)
        bottomNavigationView.selectedItemId = R.id.nav_akun
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

        val cardasbakkaca = findViewById<CardView>(R.id.card_asbakkaca)
        cardasbakkaca.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.asbakkaca,
                name = "Asbak Kaca",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Kaca"
            )
            sendSelectedTrash(trashItem)
        }
//        TODO() tambah 22 item lagi

    }
    private fun sendSelectedTrash(trashItem: TrashItem) {
        val intent = Intent()
        intent.putExtra("selected_trash_item", trashItem)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}