package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myquizapp.R.drawable.selected_option
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var selopt = 0
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        changeDay()
        textView1.setOnClickListener {
            selectedOpt(textView1,1)
        }
        textView2.setOnClickListener {
            selectedOpt(textView2,2)
        }
        textView3.setOnClickListener {
            selectedOpt(textView3,3)
        }
        textView4.setOnClickListener {
            selectedOpt(textView4,4)
        }

        btnShow.setOnClickListener {
            if (selopt == 4){
                count++
                Log.i("correct option","option 4 is selected")
                again()
            }else if(selopt == 0 ) {
                Log.i("no option","didnt select any option")
                Toast.makeText(this,"click an option",Toast.LENGTH_SHORT).show()
            }else{
                var intent = Intent(this, answer::class.java )
                Log.i("wrong option","game over")
                intent.putExtra("key", count.toString())
                startActivity(intent)
                finish()
            }
        }

    }

    fun changeDay(){

        var day = dayorder()
        var opt1 = day[0]
        var opt2 = day[1]
        var opt3 = day[2]
        var opt4 = day[3]
        var qnDate = day[4]


        var textQnHere = findViewById<TextView>(R.id.textqn)
        textQnHere.text = qnDate
        textView1.text =  opt1
        textView2.text =  opt2
        textView3.text =  opt3
        textView4.text =  opt4
    }

    fun defaultOpt(){
        var option = arrayListOf<TextView>()
        option.add(0,textView1)
        option.add(1,textView2)
        option.add(2,textView3)
        option.add(3,textView4)

        for (op in option){
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.qn_options)
            op.typeface = Typeface.DEFAULT
        }

    }

    fun selectedOpt(v:TextView,opt:Int){
        defaultOpt()
        selopt = opt
        v.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
        v.typeface = Typeface.DEFAULT_BOLD
        v.setTextColor(Color.parseColor("#000000"))
    }

    fun again(){
        defaultOpt()
        changeDay()
        textView1.setOnClickListener {
            selectedOpt(textView1,1)
        }
        textView2.setOnClickListener {
            selectedOpt(textView2,2)
        }
        textView3.setOnClickListener {
            selectedOpt(textView3,3)
        }
        textView4.setOnClickListener {
            selectedOpt(textView4,4)
        }
        selopt = 0
    }
}
