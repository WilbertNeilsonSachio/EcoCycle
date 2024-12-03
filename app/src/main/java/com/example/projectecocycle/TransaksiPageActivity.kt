package com.example.projectecocycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class TransaksiPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_page)

        loadTransactionsFromPreferences()

        val cardContainer = findViewById<LinearLayout>(R.id.card_container)
        updateNoTransactionTextVisibility(cardContainer)

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

        val alamatTujuan = intent.getStringExtra("alamat_tujuan")
        val tanggal = intent.getStringExtra("tanggal")
        val catatan = intent.getStringExtra("catatan")
        val nomorTelepon = intent.getStringExtra("nomor_telepon")
        val totalHarga = intent.getStringExtra("total_harga")

        if (!alamatTujuan.isNullOrEmpty() && !tanggal.isNullOrEmpty()) {
            // Add the transaction card
            addTransactionCard(
                alamatTujuan,
                tanggal,
                catatan ?: "",
                nomorTelepon ?: "",
                totalHarga ?: ""
            )

            // Save the transaction
            saveTransactionsToPreferences(
                alamatTujuan,
                tanggal,
                catatan ?: "",
                nomorTelepon ?: "",
                totalHarga ?: ""
            )

        }
    }

    private fun updateNoTransactionTextVisibility(cardContainer: LinearLayout) {
        val noTransactionText = findViewById<TextView>(R.id.tidak_ada_transaksi_text)
        val explanationText = findViewById<TextView>(R.id.penjelasan_transaksi_text)

        if (cardContainer.childCount > 0) {
            noTransactionText.visibility = View.GONE
            explanationText.visibility = View.GONE
        } else {
            noTransactionText.visibility = View.VISIBLE
            explanationText.visibility = View.VISIBLE
        }
    }
    private fun addTransactionCard(
        alamat: String,
        tanggal: String,
        catatan: String,
        nomorTelepon: String,
        totalHarga: String
    ) {
        val cardContainer = findViewById<LinearLayout>(R.id.card_container) // Your container layout ID

        // Inflate the card layout
        val inflater = LayoutInflater.from(this)
        val cardView = inflater.inflate(R.layout.card_transaksi, cardContainer, false) as CardView

        // Populate the card with data
        cardView.findViewById<TextView>(R.id.tv_alamat).text = "Alamat: $alamat"
        cardView.findViewById<TextView>(R.id.tv_tanggal).text = "Tanggal: $tanggal"
        cardView.findViewById<TextView>(R.id.tv_catatan).text = "Catatan: $catatan"
        cardView.findViewById<TextView>(R.id.tv_nomor_telp).text = "Nomor Telepon: $nomorTelepon"
        cardView.findViewById<TextView>(R.id.tv_jumlah_harga).text = "Jumlah Harga: $totalHarga"

        // Add the card to the container
        cardContainer.addView(cardView)
        updateNoTransactionTextVisibility(cardContainer)
    }

    private fun saveTransactionsToPreferences(
        alamat: String,
        tanggal: String,
        catatan: String,
        nomorTelepon: String,
        totalHarga: String
    ) {
        val sharedPreferences = getSharedPreferences("transactions", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Load existing transactions
        val transactionsJson = sharedPreferences.getString("transaction_list", "[]")
        val transactions: MutableList<Map<String, String>> =
            Gson().fromJson(transactionsJson, object : TypeToken<MutableList<Map<String, String>>>() {}.type)

        // Add the new transaction
        transactions.add(
            mapOf(
                "alamat" to alamat,
                "tanggal" to tanggal,
                "catatan" to catatan,
                "nomorTelepon" to nomorTelepon,
                "totalHarga" to totalHarga
            )
        )

        // Save back to SharedPreferences
        editor.putString("transaction_list", Gson().toJson(transactions))
        editor.apply()
    }
    private fun loadTransactionsFromPreferences() {
        val sharedPreferences = getSharedPreferences("transactions", Context.MODE_PRIVATE)
        val transactionsJson = sharedPreferences.getString("transaction_list", null)

        if (!transactionsJson.isNullOrEmpty()) {
            val transactions: List<Map<String, String>> =
                Gson().fromJson(transactionsJson, object : TypeToken<List<Map<String, String>>>() {}.type)

            findViewById<LinearLayout>(R.id.card_container)
            for (transaction in transactions) {
                addTransactionCard(
                    transaction["alamat"] ?: "",
                    transaction["tanggal"] ?: "",
                    transaction["catatan"] ?: "",
                    transaction["nomorTelepon"] ?: "",
                    transaction["totalHarga"] ?: ""
                )
            }
        }

        // Update the visibility of no-transaction texts
        updateNoTransactionTextVisibility(findViewById(R.id.card_container))
    }
}