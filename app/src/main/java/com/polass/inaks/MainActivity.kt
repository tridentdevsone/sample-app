package com.polass.inaks

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.MainThread
import androidx.annotation.RequiresApi
import com.polass.inaks.splashes.MainMenuActivity

class MainActivity : AppCompatActivity() {   //, CallBack - коллбек серого клиента
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        /*
        Тут будет инициализация клиента, оставить таймаут ниже для демонстрации корректной работы загрузочного экрана
         */

        Handler().postDelayed({
            startGame()
        }, 4000)


    }

   // override - выполняемый в дальнейшем коллбек для перехода на игру
    fun startGame() {
        startActivity(Intent(this, MainMenuActivity::class.java))
        finish()
    }
}