package com.mymemory

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Play : AppCompatActivity() {

    // variables para los componentes de la vista
    var imb00: ImageButton? = null
    var imb01: ImageButton? = null
    var imb02: ImageButton? = null
    var imb03: ImageButton? = null
    var imb04: ImageButton? = null
    var imb05: ImageButton? = null
    var imb06: ImageButton? = null
    var imb07: ImageButton? = null
    var imb08: ImageButton? = null
    var imb09: ImageButton? = null
    var imb10: ImageButton? = null
    var imb11: ImageButton? = null
    var imb12: ImageButton? = null
    var imb13: ImageButton? = null
    var imb14: ImageButton? = null
    var imb15: ImageButton? = null
    var tablero = arrayOfNulls<ImageButton>(16)
    private lateinit var botonReiniciar: Button
    private lateinit var botonSalir: Button
    private lateinit var  textoPuntuacion: TextView
    var puntuacion = 0
    var aciertos = 0

    //imagenes
    lateinit var imagenes: IntArray
    var fondo = 0

    //variables del juego
    var arrayDesordenado: ArrayList<Int>? = null
    var primero: ImageButton? = null
    var numeroPrimero = 0
    var numeroSegundo: Int = 0
    var bloqueo = false
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        init()
    }

    private fun cargarTablero() {
        imb00 = findViewById(R.id.boton00)
        imb01 = findViewById(R.id.boton01)
        imb02 = findViewById(R.id.boton02)
        imb03 = findViewById(R.id.boton03)
        imb04 = findViewById(R.id.boton04)
        imb05 = findViewById(R.id.boton05)
        imb06 = findViewById(R.id.boton06)
        imb07 = findViewById(R.id.boton07)
        imb08 = findViewById(R.id.boton08)
        imb09 = findViewById(R.id.boton09)
        imb10 = findViewById(R.id.boton10)
        imb11 = findViewById(R.id.boton11)
        imb12 = findViewById(R.id.boton12)
        imb13 = findViewById(R.id.boton13)
        imb14 = findViewById(R.id.boton14)
        imb15 = findViewById(R.id.boton15)
        tablero[0] = imb00
        tablero[1] = imb01
        tablero[2] = imb02
        tablero[3] = imb03
        tablero[4] = imb04
        tablero[5] = imb05
        tablero[6] = imb06
        tablero[7] = imb07
        tablero[8] = imb08
        tablero[9] = imb09
        tablero[10] = imb10
        tablero[11] = imb11
        tablero[12] = imb12
        tablero[13] = imb13
        tablero[14] = imb14
        tablero[15] = imb15
    }

    private fun cargarBotones() {
        botonReiniciar = findViewById(R.id.botonJuegoReiniciar)
        botonSalir = findViewById(R.id.botonJuegoSalir)
        botonReiniciar.setOnClickListener { init() }
        botonSalir.setOnClickListener { finish() }
    }

    @SuppressLint("SetTextI18n")
    private fun cargarTexto() {
        textoPuntuacion = findViewById(R.id.texto_puntuacion)
        puntuacion = 0
        aciertos = 0
        textoPuntuacion.text = "Puntuacion: $puntuacion"
    }

    private fun cargarImagenes() {
        imagenes = intArrayOf(
            R.drawable.xx_01_madagascar,
            R.drawable.xx_02_shrek,
            R.drawable.xx_03_minions,
            R.drawable.xx_04_avatar,
            R.drawable.xx_05_nemo,
            R.drawable.xx_06_rio,
            R.drawable.xx_07_transformania,
            R.drawable.xx_08_eradehielo
        )
        fondo = R.drawable.fondo
    }

    private fun barajar(longitud: Int): ArrayList<Int> {
        val result = ArrayList<Int>()
        for (i in 0 until longitud * 2) {
            result.add(i % longitud)
        }
        result.shuffle()
        // System.out.println(Arrays.toString(result.toArray()));
        return result
    }

    @SuppressLint("SetTextI18n")
    private fun comprobar(i: Int, imgb: ImageButton) {
        if (primero == null) {
            primero = imgb
            primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
            primero!!.setImageResource(imagenes[arrayDesordenado!![i]])
            primero!!.isEnabled = false
            numeroPrimero = arrayDesordenado!![i]
        } else {
            bloqueo = true
            imgb.scaleType = ImageView.ScaleType.CENTER_CROP
            imgb.setImageResource(imagenes[arrayDesordenado!![i]])
            imgb.isEnabled = false
            numeroSegundo = arrayDesordenado!![i]
            if (numeroPrimero == numeroSegundo) {
                primero = null
                bloqueo = false
                aciertos++
                puntuacion++
                textoPuntuacion.text = "Puntuación: $puntuacion"
                if (aciertos == imagenes.size) {
                    val toast =
                        Toast.makeText(applicationContext, "Has ganado!!", Toast.LENGTH_LONG)
                    toast.show()
                }
            } else {
                handler.postDelayed({
                    primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    primero!!.setImageResource(fondo)
                    primero!!.isEnabled = true
                    imgb.scaleType = ImageView.ScaleType.CENTER_CROP
                    imgb.setImageResource(fondo)
                    imgb.isEnabled = true
                    bloqueo = false
                    primero = null
                    puntuacion--
                    textoPuntuacion.text = "Puntuación: $puntuacion"
                }, 1000)
            }
        }
    }

    private fun init() {
        cargarTablero()
        cargarBotones()
        cargarTexto()
        cargarImagenes()
        arrayDesordenado = barajar(imagenes.size)
        for (i in tablero.indices) {
            tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
            tablero[i]!!.setImageResource(imagenes[arrayDesordenado!![i]])
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed({
            for (i in tablero.indices) {
                tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
                //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                tablero[i]!!.setImageResource(fondo)
            }
        }, 500)
        for (i in tablero.indices) {
            tablero[i]!!.isEnabled = true
            tablero[i]!!.setOnClickListener { if (!bloqueo) comprobar(i, tablero[i]!!) }
        }
    }

}