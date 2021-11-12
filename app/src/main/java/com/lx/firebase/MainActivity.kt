package com.lx.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.lx.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            firebaseAnalytics.logEvent("click"){
                param("type", "jump")
            }
        }
        firebaseAnalytics = Firebase.analytics
        val parameters = Bundle().apply {
            this.putString("level_name", "Caverns01")
            this.putInt("level_difficulty", 4)
        }
        firebaseAnalytics.setDefaultEventParameters(parameters)
        firebaseAnalytics.logEvent("share_image") {
            param("image_name", "name")
            param("full_text", "text")
        }
        MobileAds.initialize(this) {}
    }
}