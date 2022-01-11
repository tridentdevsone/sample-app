package com.polass.inaks.splashes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.polass.inaks.R
import com.polass.inaks.playingto.MainActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        var about = findViewById<Button>(R.id.imageButton)
        var startButton: Button = findViewById(R.id.button2)


        about.setOnClickListener{
            startActivity(Intent(this@MainMenuActivity, AboutActivity::class.java))
        }

        startButton.setOnClickListener{
            startActivity(Intent(this@MainMenuActivity, MainActivity::class.java))
        }
    }
}