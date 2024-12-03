package com.example.projectecocycle

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectecocycle.dataclass.TrashItem

class SalePageActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
            launchTrashPicker.launch(intent)
        }

        val alamatTujuan = findViewById<TextView>(R.id.alamattujuan)
        val fullAddress = intent.getStringExtra("alamattujuan") ?: getString(R.string.tv_pilihalamattujuan)
        alamatTujuan.text = fullAddress

        launchTrashPicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val trashItem = result.data?.getParcelableExtra("selected_trash_item", TrashItem::class.java)
                trashItem?.let {
                    addTrashCard(it) // Add the selected trash item
                }
            }
        }

    }
    private lateinit var launchTrashPicker: ActivityResultLauncher<Intent>

    private fun addTrashCard(trashItem: TrashItem) {
        // Find the container
        val cardContainer = findViewById<LinearLayout>(R.id.card_container)

        // Inflate the card layout
        val inflater = LayoutInflater.from(this)
        val cardView = inflater.inflate(R.layout.card_tambah_sampah, cardContainer, false) as CardView

        // Populate the card with data
        val imageView = cardView.findViewById<ImageView>(R.id.item_image)
        val nameTextView = cardView.findViewById<TextView>(R.id.item_name)
        val priceTextView = cardView.findViewById<TextView>(R.id.item_price)
        val unitTextView = cardView.findViewById<TextView>(R.id.item_unit)
        val tagTextView = cardView.findViewById<TextView>(R.id.item_tag)

        imageView.setImageResource(trashItem.imageResId)
        nameTextView.text = trashItem.name
        priceTextView.text = trashItem.price
        unitTextView.text = trashItem.unit
        tagTextView.text = trashItem.tag

        // Add the card to the container
        cardContainer.addView(cardView)
    }

}