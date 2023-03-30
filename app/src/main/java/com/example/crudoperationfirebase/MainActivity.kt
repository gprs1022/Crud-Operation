package com.example.crudoperationfirebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var insertButton: Button
    private lateinit var fetchingButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val insertButton = findViewById<Button>(R.id.insertData)
        val fetchingButton = findViewById<Button>(R.id.fetchingData)

        insertButton.setOnClickListener {

            val intent = Intent(this,insertData::class.java)
            startActivity(intent)
        }

        fetchingButton.setOnClickListener {

            val intent = Intent(this, fetchingData::class.java)

            startActivity(intent)
        }
    }
}