package com.example.projectecocycle

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SalePageActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_page)

        val cardContainer = findViewById<LinearLayout>(R.id.card_container)
        val detailsSampahCard = findViewById<CardView>(R.id.detailsampah_card)
        updateDetailsSampahCardVisibility(cardContainer, detailsSampahCard)

        val notificon = findViewById<ImageView>(R.id.notificon)
        notificon.setOnClickListener {
            val intent = Intent(this, NotificationPageActivity::class.java)
            startActivity(intent)
        }

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

        val editTextDate = findViewById<EditText>(R.id.isitanggal)

        // Set up a Calendar instance
        val calendar = Calendar.getInstance()

        // Set an OnClickListener to show the DatePickerDialog
        editTextDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    // Set selected date on the EditText
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    editTextDate.setText(dateFormat.format(selectedDate.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        val kirimBtn = findViewById<Button>(R.id.kirimbtn)
        kirimBtn.setOnClickListener {
            val alamatTujuan = findViewById<TextView>(R.id.alamattujuan).text.toString()
            val tanggal = findViewById<EditText>(R.id.isitanggal).text.toString()
            val catatan = findViewById<EditText>(R.id.isicatatan).text.toString()
            val nomorTelepon = findViewById<EditText>(R.id.isinomortelepon).text.toString()
            val totalHarga = findViewById<TextView>(R.id.totalhargatext).text.toString()

            val intent = Intent(this, TransaksiPageActivity::class.java).apply {
                putExtra("alamat_tujuan", alamatTujuan)
                putExtra("tanggal", tanggal)
                putExtra("catatan", catatan)
                putExtra("nomor_telepon", nomorTelepon)
                putExtra("total_harga", totalHarga)
            }
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()

        val cardContainer = findViewById<LinearLayout>(R.id.card_container)
        val detailsSampahCard = findViewById<CardView>(R.id.detailsampah_card)

        // Restore the visibility based on the flag
        if (isDetailCardVisible && cardContainer.childCount > 0) {
            detailsSampahCard.visibility = View.VISIBLE
        } else {
            detailsSampahCard.visibility = View.GONE
        }
    }


    private lateinit var launchTrashPicker: ActivityResultLauncher<Intent>

    private var totalPrice: Int = 0

    private fun addTrashCard(trashItem: TrashItem) {
        // Find the container
        val cardContainer = findViewById<LinearLayout>(R.id.card_container)
        val detailsSampahCard = findViewById<CardView>(R.id.detailsampah_card)
        val totalPriceTextView = findViewById<TextView>(R.id.totalhargatext)

        // Inflate the card layout
        val inflater = LayoutInflater.from(this)
        val cardView = inflater.inflate(R.layout.card_tambah_sampah, cardContainer, false) as CardView

        // Populate the card with data
        val imageView = cardView.findViewById<ImageView>(R.id.item_image)
        val nameTextView = cardView.findViewById<TextView>(R.id.item_name)
        val priceTextView = cardView.findViewById<TextView>(R.id.item_price)
        val unitTextView = cardView.findViewById<TextView>(R.id.item_unit)
        val tagTextView = cardView.findViewById<TextView>(R.id.item_tag)
        val quantityTextView = cardView.findViewById<TextView>(R.id.item_quantity)
        val buttonPlus = cardView.findViewById<Button>(R.id.button_plus)
        val buttonMinus = cardView.findViewById<Button>(R.id.button_minus)

        imageView.setImageResource(trashItem.imageResId)
        nameTextView.text = trashItem.name
        priceTextView.text = trashItem.price
        unitTextView.text = trashItem.unit
        tagTextView.text = trashItem.tag

        val itemPrice = trashItem.price.replace("[^\\d]".toRegex(), "").toInt()

        var quantity = 1
        quantityTextView.text = quantity.toString()

        // Update the total price
        totalPrice += itemPrice * quantity
        updateTotalPrice(totalPriceTextView, totalPrice)

        // Handle plus button click
        buttonPlus.setOnClickListener {
            quantity++
            quantityTextView.text = quantity.toString()
            totalPrice += itemPrice
            updateTotalPrice(totalPriceTextView, totalPrice)
        }

        // Handle minus button click
        buttonMinus.setOnClickListener {
            if (quantity > 0) {
                quantity--
                quantityTextView.text = quantity.toString()
                totalPrice -= itemPrice
                updateTotalPrice(totalPriceTextView, totalPrice)
            }
            if (quantity == 0) {
                // Remove card
                cardContainer.removeView(cardView)
                updateDetailsSampahCardVisibility(cardContainer, detailsSampahCard)
            }
        }

        // Add the card to the container
        cardContainer.addView(cardView)
        updateDetailsSampahCardVisibility(cardContainer, detailsSampahCard)
    }

    private fun updateTotalPrice(totalPriceTextView: TextView, totalPrice: Int) {
        totalPriceTextView.text = "Rp $totalPrice"
    }

    private fun updateDetailsSampahCardVisibility(cardContainer: LinearLayout, detailsSampahCard: CardView) {
        if (cardContainer.childCount > 0) {
            detailsSampahCard.visibility = View.VISIBLE
            isDetailCardVisible = true
        } else {
            detailsSampahCard.visibility = View.GONE
            isDetailCardVisible = false
        }
    }

    private var isDetailCardVisible = false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isDetailCardVisible", isDetailCardVisible)
        val tanggal = findViewById<EditText>(R.id.isitanggal).text.toString()
        val catatan = findViewById<EditText>(R.id.isicatatan).text.toString()
        val nomorTelepon = findViewById<TextView>(R.id.nomortelepontext).text.toString()

        outState.putString("tanggal", tanggal)
        outState.putString("catatan", catatan)
        outState.putString("nomorTelepon", nomorTelepon)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isDetailCardVisible = savedInstanceState.getBoolean("isDetailCardVisible", false)
        val tanggal = savedInstanceState.getString("tanggal", "")
        val catatan = savedInstanceState.getString("catatan", "")
        val nomorTelepon = savedInstanceState.getString("nomorTelepon", "")

        findViewById<EditText>(R.id.isitanggal).setText(tanggal)
        findViewById<EditText>(R.id.isicatatan).setText(catatan)
        findViewById<TextView>(R.id.nomortelepontext).text = nomorTelepon
    }

}