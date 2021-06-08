package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_answer.*

class answer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val msg = intent.getStringExtra("key")
        textView6.text = msg

        button.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        button2.setOnClickListener {
            finish()
        }
    }
}