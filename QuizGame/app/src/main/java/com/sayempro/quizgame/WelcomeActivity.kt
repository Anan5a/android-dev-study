package com.sayempro.quizgame

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sayempro.quizgame.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var splashBinding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashBinding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = splashBinding.root

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val alphaAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.splash_anim)
        splashBinding.textViewSplash.startAnimation(alphaAnimation)
        splashBinding.imageView.startAnimation(alphaAnimation)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            object : Runnable {
                override fun run() {
                    val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
//                    val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            },
            2500,
        )
    }
}
