package com.example.projectecocycle

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoadingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_page)
        val logoEcoCycleLight = findViewById<ImageView>(R.id.logoEcoCycleLight)
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        fadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@LoadingPageActivity, LoginPageActivity::class.java)
                startActivity(intent)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        logoEcoCycleLight.startAnimation(fadeInAnimation)
    }
}
