package com.example.projectecocycle

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectecocycle.dataclass.TrashItem

class SalePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_page)

        val addbuttonaddress = findViewById<Button>(R.id.addbuttonaddress)
        addbuttonaddress.setOnClickListener {
            val intent = Intent(this, LocationPageActivity::class.java)
            startActivity(intent)
        }

        val tambahsampahbtn = findViewById<Button>(R.id.tambahsampahbtn)
        tambahsampahbtn.setOnClickListener {
            val intent = Intent(this, DraftAllTrashPageActivity::class.java)
            startActivity(intent)
        }

        val alamatTujuan = findViewById<TextView>(R.id.alamattujuan)
        val fullAddress = intent.getStringExtra("alamattujuan") ?: getString(R.string.tv_pilihalamattujuan)
        alamatTujuan.text = fullAddress

    }

}