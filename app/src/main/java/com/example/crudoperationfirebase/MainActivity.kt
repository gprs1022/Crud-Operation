package com.example.crudoperationfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var insertButton: Button
    private lateinit var fetchingButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insertButton = findViewById(R.id.insertData)
        fetchingButton = findViewById(R.id.fetchingData)

        insertButton.setOnClickListener {

            val intent = Intent(this,InsertData::class.java)
            startActivity(intent)
        }

        fetchingButton.setOnClickListener {

            val fetchIntent = Intent(this, FetchingData::class.java)
            startActivity(fetchIntent)
        }
    }
}