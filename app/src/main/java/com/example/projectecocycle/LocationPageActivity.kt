package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LocationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_page)
        val etNamaTempat = findViewById<EditText>(R.id.et_nama_tempat)
        val etProvince = findViewById<EditText>(R.id.et_province)
        val etKabupaten = findViewById<EditText>(R.id.et_kabupaten)
        val alamatText = findViewById<EditText>(R.id.alamattext)
        val kirimbtn = findViewById<Button>(R.id.kirimbtn)

        kirimbtn.setOnClickListener {
            val namaTempat = etNamaTempat.text.toString().trim()
            val province = etProvince.text.toString().trim()
            val kabupaten = etKabupaten.text.toString().trim()
            val alamat = alamatText.text.toString().trim()

            val fullAddress = "$province, $kabupaten, $alamat, $namaTempat"

            // Pass the concatenated text to SalePageActivity
            val intent = Intent(this, SalePageActivity::class.java)
            intent.putExtra("alamattujuan", fullAddress)
            startActivity(intent)
        }
    }
}