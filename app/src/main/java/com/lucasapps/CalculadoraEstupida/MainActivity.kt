package com.lucasapps.CalculadoraEstupida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    var variavelX:Int = 0
    var variavelY:Int = 0
    var resultado:Int = 0

    val setVariavelXlauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
            if (result.resultCode == RESULT_OK){
                variavelX = result.data!!.getIntExtra("valor", 0)
                val textViewX = findViewById<TextView>(R.id.textViewX)
                textViewX.text = "${variavelX}"
            }else{
                Toast.makeText(this,"cancelado", Toast.LENGTH_SHORT).show()
            }
    }
    val setVariavelYlauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
            if(result.resultCode == RESULT_OK){
                variavelY = result.data!!.getIntExtra("valor", 0)
                val textViewY = findViewById<TextView>(R.id.textViewY)
                textViewY.text ="${variavelY}"
            }else{
                Toast.makeText(this,"cancelado", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewX = findViewById<TextView>(R.id.textViewX)
        val textViewY = findViewById<TextView>(R.id.textViewY)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        textViewX.text = "${variavelX}"
        textViewY.text = "${variavelY}"
        textViewResultado.text ="${resultado}"

        val buttonSetVariavelX = findViewById<Button>(R.id.buttonSetVariavelX)
        val buttonSetVariavelY = findViewById<Button>(R.id.buttonSetVariavelY)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)

        buttonSetVariavelX.setOnClickListener {
            val i = Intent (this, recebeActivity::class.java)
            val b = Bundle()
            b.putString("label","variavel x")
            b.putInt("valor", variavelX)
            i.putExtras(b)

            setVariavelXlauncher.launch(i)
        }
        buttonSetVariavelY.setOnClickListener {
            val i = Intent(this, recebeActivity::class.java)
            val b = Bundle()
            b.putString("label", "variavel y")
            b.putInt("valor", variavelY)
            i.putExtras(b)

            setVariavelYlauncher.launch(i)
        }

        buttonCalcular.setOnClickListener {
            Toast.makeText(this, " Cálculo Realizado", Toast.LENGTH_SHORT).show()
            textViewResultado.text = "${variavelX + variavelY}"
        }
    }
}