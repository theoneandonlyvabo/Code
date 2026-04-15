package com.app.foodorder.mygeometryapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class CylinderActivity : AppCompatActivity() {

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
        tvTitle.text = "Volume Tabung"
        tvLabel1.text = "Jari-jari (r) cm"
        tvLabel2.text = "Tinggi (t) cm"

        tvLabel3.visibility = View.GONE
        etInput3.visibility = View.GONE

        btnHitung.setOnClickListener {
            val r = etInput1.text.toString().toDoubleOrNull()
                ?: return@setOnClickListener

            val t = etInput2.text.toString().toDoubleOrNull()
                ?: return@setOnClickListener

            val result = Math.PI * r * r * t
            val df = DecimalFormat("#.##")

            tvResult.text = "Volume = ${df.format(result)} cm³"
        }
    }
}