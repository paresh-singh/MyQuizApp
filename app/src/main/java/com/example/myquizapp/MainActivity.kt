package com.example.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeDay()
    }

    fun changeDay(){
        var day = dayorder()
        var opt1 = day[0]
        var opt2 = day[1]
        var opt3 = day[2]
        var opt4 = day[3]
        var qnDate = day[4]

        var textView1 = findViewById<TextView>(R.id.textView1)
        var textView2 = findViewById<TextView>(R.id.textView2)
        var textView3 = findViewById<TextView>(R.id.textView3)
        var textView4 = findViewById<TextView>(R.id.textView4)
        var textQnHere = findViewById<TextView>(R.id.textqn)
        textQnHere.text = qnDate
        textView1.text =  opt1
        textView2.text =  opt2
        textView3.text =  opt3
        textView4.text =  opt4
    }
}