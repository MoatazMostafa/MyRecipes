package com.android.myrecipes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import com.android.myrecipes.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({// Splash screen for 3 sec.
            run {
                val intent = Intent(applicationContext, RecipeListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}