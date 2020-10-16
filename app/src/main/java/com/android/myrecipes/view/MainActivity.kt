package com.android.myrecipes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.android.myrecipes.R

class MainActivity : AppCompatActivity() {
    private var delayHandler: Handler? = null
    private val delayTime: Long = 2000 //2 seconds splash delay
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        delayHandler = Handler()
        delayHandler!!.postDelayed(mRunnable, delayTime)
    }
    public override fun onDestroy() {
        if (delayHandler != null) {
            delayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }


    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, RecipeListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}