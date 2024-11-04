package com.example.projectecocycle

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoadingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading_page)
        val logoEcoCycleLight = findViewById<ImageView>(R.id.logoEcoCycleLight)
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logoEcoCycleLight.startAnimation(fadeInAnimation)
    }
}
