package com.app.foodorder.mygeometryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCircle = findViewById<Button>(R.id.btn_circle)
        val btnTrapezoid = findViewById<Button>(R.id.btn_trapezoid)
        val btnCylinder = findViewById<Button>(R.id.btn_cylinder)

        btnCircle.setOnClickListener {
            startActivity(Intent(this, CircleActivity::class.java))
        }

        btnTrapezoid.setOnClickListener {
            startActivity(Intent(this, TrapezoidActivity::class.java))
        }

        btnCylinder.setOnClickListener {
            startActivity(Intent(this, CylinderActivity::class.java))
        }
    }
}