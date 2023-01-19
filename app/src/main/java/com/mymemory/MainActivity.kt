package com.mymemory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var play: Button
    private lateinit var salir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play = findViewById<Button>(R.id.botonMainJugar)
        salir = findViewById<Button>(R.id.botonMainSalir)

        play.setOnClickListener {
            println("iniciando juego...")
            iniciarJuego()
        }

        salir.setOnClickListener { finish() }
    }

    private fun iniciarJuego() {
        val i = Intent(this, Play::class.java)
        startActivity(i)
    }
}