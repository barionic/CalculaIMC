package com.barionic.calculaimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso: EditText //ou private var etPeso : EditText = EditText(this)
    private lateinit var etAltura: EditText

    private lateinit var tvResultado: TextView

    private lateinit var btCalcular: Button
    private lateinit var btLimpar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //ou SEM DECLARAR O lateinit: val etPeso = findViewById<EditText>(R.id.etPeso)
        //desta forma só poderá ser usado dentro do onCreate
        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnClickListener {
            btCalcularOnClick()
        }

        btLimpar.setOnClickListener {
            btLimparOnClick()
        }

        btCalcular.setOnLongClickListener {
            Toast.makeText(this, getString(R.string.msg_toast_calcular), Toast.LENGTH_SHORT).show()
            true
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText(this, getString(R.string.msg_toast_limpar), Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun btCalcularOnClick() {
        var resultado: Double

        val peso = etPeso.text.toString().toDoubleOrNull()?: run { //failback
            etPeso.error = getString(R.string.erro_peso)
            etPeso.requestFocus()
            return
        }
        val altura = etAltura.text.toString().toDoubleOrNull()?: run {
            etAltura.error = getString(R.string.erro_altura)
            etAltura.requestFocus()
            return
        }

        resultado = peso / altura.pow(2)

        tvResultado.text = "%.2f".format(resultado)
    }

    private fun btLimparOnClick() {
        etPeso.text.clear() //ou etPeso.setText("")
        etAltura.text.clear()
        tvResultado.text = getString(R.string.zeros_inciais)
        etPeso.requestFocus()
    }

}