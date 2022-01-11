package com.polass.inaks.splashes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.polass.inaks.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        var exit: Button = findViewById(R.id.button3)

        exit.setOnClickListener{
            startActivity(Intent(this@AboutActivity, MainMenuActivity::class.java))
        }
    }
}