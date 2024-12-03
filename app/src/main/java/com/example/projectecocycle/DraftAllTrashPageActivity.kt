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
        val cardbotolairmineral = findViewById<CardView>(R.id.card_botolairmineral)
        cardbotolairmineral.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.botolairmineral,
                name = "Botol Air Mineral",
                price = "Rp 2.200",
                unit = "Kg",
                tag = "Plastik"
            )
            sendSelectedTrash(trashItem)
        }
        val cardbotolkaca = findViewById<CardView>(R.id.card_botolkaca)
        cardbotolkaca.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.botolkaca,
                name = "Botol Kaca",
                price = "Rp 1.500",
                unit = "Kg",
                tag = "Kaca"
            )
            sendSelectedTrash(trashItem)
        }
        val cardbuku = findViewById<CardView>(R.id.card_buku)
        cardbuku.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.buku,
                name = "Buku",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardbungkussnack = findViewById<CardView>(R.id.card_bungkussnack)
        cardbungkussnack.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.bungkusplastik,
                name = "Bungkus Snack",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Plastik"
            )
            sendSelectedTrash(trashItem)
        }
        val cardcangkirkaca = findViewById<CardView>(R.id.card_cangkirkaca)
        cardcangkirkaca.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.cangkirkaca,
                name = "Cangkir Kaca",
                price = "Rp 1.500",
                unit = "Kg",
                tag = "Kaca"
            )
            sendSelectedTrash(trashItem)
        }
        val cardduplek = findViewById<CardView>(R.id.card_duplek)
        cardduplek.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.duplek,
                name = "Duplek",
                price = "Rp 2.200",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardgalon = findViewById<CardView>(R.id.card_galon)
        cardgalon.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.galon,
                name = "Galon",
                price = "Rp 2.200",
                unit = "Kg",
                tag = "Plastik"
            )
            sendSelectedTrash(trashItem)
        }
        val cardgelaskaca = findViewById<CardView>(R.id.card_gelaskaca)
        cardgelaskaca.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.gelaskaca,
                name = "Gelas Kaca",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Kaca"
            )
            sendSelectedTrash(trashItem)
        }
        val cardgelasplastik = findViewById<CardView>(R.id.card_gelasplastik)
        cardgelasplastik.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.gelasplastik,
                name = "Gelas Plastik",
                price = "Rp 1.500",
                unit = "Kg",
                tag = "Plastik"
            )
            sendSelectedTrash(trashItem)
        }
        val cardgulungantisu = findViewById<CardView>(R.id.card_gulungantisu)
        cardgulungantisu.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.gulungantisu,
                name = "Gulungan Tisu",
                price = "Rp 2.200",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardkantongplastik = findViewById<CardView>(R.id.card_kantongplastik)
        cardkantongplastik.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.kantongplastik,
                name = "Kantong Plastik",
                price = "Rp 5.000",
                unit = "Kg",
                tag = "Plastik"
            )
            sendSelectedTrash(trashItem)
        }
        val cardkardus = findViewById<CardView>(R.id.card_kardus)
        cardkardus.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.kardus,
                name = "Kardus",
                price = "Rp 2.200",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardkertasbon = findViewById<CardView>(R.id.card_kertasbon)
        cardkertasbon.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.kertasbon,
                name = "Kertas Bon",
                price = "Rp 1.500",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardkertascacah = findViewById<CardView>(R.id.card_kertascacah)
        cardkertascacah.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.kertascacah,
                name = "Kertas Cacah",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardkertashvs = findViewById<CardView>(R.id.card_kertashvs)
        cardkertashvs.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.kertashvs,
                name = "Kertas HVS",
                price = "Rp 1.500",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardkoran= findViewById<CardView>(R.id.card_koran)
        cardkoran.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.koran,
                name = "Koran",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardpiringkaca = findViewById<CardView>(R.id.card_piringkaca)
        cardpiringkaca.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.piringkaca,
                name = "Piring Kaca",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Kaca"
            )
            sendSelectedTrash(trashItem)
        }
        val cardsarangtelur = findViewById<CardView>(R.id.card_sarangtelur)
        cardsarangtelur.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.sarangtelur,
                name = "Sarang Telur",
                price = "Rp 1.500",
                unit = "Kg",
                tag = "Kertas/Kardus"
            )
            sendSelectedTrash(trashItem)
        }
        val cardtekokaca = findViewById<CardView>(R.id.card_tekokaca)
        cardtekokaca.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.tekokaca,
                name = "Teko Kaca",
                price = "Rp 2.200",
                unit = "Kg",
                tag = "Kaca"
            )
            sendSelectedTrash(trashItem)
        }
        val cardtempatcat = findViewById<CardView>(R.id.card_tempatcat)
        cardtempatcat.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.tempatcat,
                name = "Tempat Cat",
                price = "Rp 2.200",
                unit = "Kg",
                tag = "Plastik"
            )
            sendSelectedTrash(trashItem)
        }
        val cardtopleskaca = findViewById<CardView>(R.id.card_topleskaca)
        cardtopleskaca.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.topleskaca,
                name = "Toples Kaca",
                price = "Rp 3.500",
                unit = "Kg",
                tag = "Kaca"
            )
            sendSelectedTrash(trashItem)
        }
        val cardtutupbotol = findViewById<CardView>(R.id.card_tutupbotol)
        cardtutupbotol.setOnClickListener {
            val trashItem = TrashItem(
                imageResId = R.drawable.tutupbotol,
                name = "Tutup Botol",
                price = "Rp 3.000",
                unit = "Kg",
                tag = "Plastik"
            )
            sendSelectedTrash(trashItem)
        }

    }
    private fun sendSelectedTrash(trashItem: TrashItem) {
        val intent = Intent()
        intent.putExtra("selected_trash_item", trashItem)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}