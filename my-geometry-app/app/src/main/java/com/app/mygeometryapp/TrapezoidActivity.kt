package com.app.foodorder.mygeometryapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class TrapezoidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvLabel1 = findViewById<TextView>(R.id.tv_label1)
        val tvLabel2 = findViewById<TextView>(R.id.tv_label2)
        val tvLabel3 = findViewById<TextView>(R.id.tv_label3)

        val etInput1 = findViewById<EditText>(R.id.et_input1)
        val etInput2 = findViewById<EditText>(R.id.et_input2)
        val etInput3 = findViewById<EditText>(R.id.et_input3)

        val btnHitung = findViewById<Button>(R.id.btn_hitung)
        val tvResult = findViewById<TextView>(R.id.tv_result)

        // setup UI
        tvTitle.text = "Luas Trapesium"
        tvLabel1.text = "Sisi Sejajar 1 (a) cm"
        tvLabel2.text = "Sisi Sejajar 2 (b) cm"
        tvLabel3.text = "Tinggi (t) cm"

        btnHitung.setOnClickListener {
            val a = etInput1.text.toString().toDoubleOrNull()
                ?: return@setOnClickListener

            val b = etInput2.text.toString().toDoubleOrNull()
                ?: return@setOnClickListener

            val t = etInput3.text.toString().toDoubleOrNull()
                ?: return@setOnClickListener

            val result = 0.5 * (a + b) * t
            val df = DecimalFormat("#.##")

            tvResult.text = "Luas = ${df.format(result)} cm²"
        }
    }
}