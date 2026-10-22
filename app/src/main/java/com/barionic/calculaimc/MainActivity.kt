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
import java.text.DecimalFormat
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

        etPeso = findViewById(R.id.etPeso)
        //ou SEM DECLARAR O lateinit: val etPeso = findViewById<EditText>(R.id.etPeso)
        //desta forma só poderá ser usado dentro do onCreate
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnClickListener {
            btCalcularOnClick()
//            Toast.makeText(
//                this,
//                "Botão Calcular Clicado",
//                Toast.LENGTH_SHORT
//            ).show()
        }

        btLimpar.setOnClickListener {
            btLimparOnClick()
        }

        btCalcular.setOnLongClickListener {
            Toast.makeText(this, "Botão para calcular IMC", Toast.LENGTH_SHORT).show()
            true
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText(this, "Botão para limpar a tela", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun btCalcularOnClick() {
        //entrada
            //val campo1 = etPeso.text.toString()
            //val campo2 = etAltura.text.toString()
        var resultado = 0.0

        val peso = etPeso.text.toString().toDoubleOrNull()?: run { //failback
            etPeso.error = "Campo peso deve ser informado"
            etPeso.requestFocus()
            return
        }
        val altura = etAltura.text.toString().toDoubleOrNull()?: run {
            etAltura.error = "Campo altura deve ser informado"
            etAltura.requestFocus()
            return
        }

        //processamento
            //resultado = peso / (altura * altura) //ou resultado = peso / Math.pow(altura, 2.0)
            //if (peso == null){
            //    etPeso.error = "Campo peso deve ser informado"
            //    return
            //}
            //if (altura == null){
            //    etAltura.error = "Campo altura deve ser informado"
            //    return
            //}
        resultado = peso / altura.pow(2)

        //saída
            //val df = DecimalFormat("0.00")
            //val msg: String = df.format(resultado)
        tvResultado.text = "%.2f".format(resultado)
    }

    private fun btLimparOnClick() {
        etPeso.text.clear() //ou etPeso.setText("")
        etAltura.text.clear()
        tvResultado.text = "0.0"
        etPeso.requestFocus()
    }

}