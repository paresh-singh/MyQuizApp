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
    var selopt = ""
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        again()

    }

    fun changeDay(){

        val day = dayorder()
        val opt1 = day[0]
        val opt2 = day[1]
        val opt3 = day[2]
        val opt4 = day[3]
        val qnDate = day[4]
        val list = arrayListOf<String>(opt1 , opt2 , opt3 , opt4)

        var textQnHere = findViewById<TextView>(R.id.textqn)
        textQnHere.text = qnDate
        textView1.text =  list.random()
        textView2.text =  list.random()
        textView3.text =  list.random()
        textView4.text =  list.random()

        while (true){
            if (textView1.text == textView2.text){
                textView2.text =  list.random()
            }else if (textView1.text == textView3.text || textView3.text == textView2.text){
                textView3.text =  list.random()
            }else if (textView1.text == textView4.text || textView3.text == textView4.text || textView4.text == textView2.text ){
                textView4.text =  list.random()
            }else {
                if (textView1.text != textView4.text && textView3.text != textView4.text && textView4.text != textView2.text && textView1.text != textView3.text && textView3.text != textView2.text && textView1.text != textView2.text){
                    break
                }
            }
        }


        btnShow.setOnClickListener {
            if (selopt == opt4){
                count++
                Log.i("correct option","option 4 is selected")
                again()
            }else if(selopt == opt1 || selopt ==opt2 || selopt==opt3 ) {
                var intent = Intent(this, answer::class.java )
                Log.i("wrong option","game over")
                intent.putExtra("key", count.toString())
                startActivity(intent)
                finish()

            }else{
                Log.i("no option","didnt select any option")
                Toast.makeText(this,"click an option",Toast.LENGTH_SHORT).show()
            }
        }
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

    fun selectedOpt(v:TextView){
        defaultOpt()
        selopt = v.text.toString()
        v.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
        v.typeface = Typeface.DEFAULT_BOLD
        v.setTextColor(Color.parseColor("#000000"))
    }

    fun again(){
        defaultOpt()
        changeDay()
        textView5.text = "Score is " + count.toString()
        textView1.setOnClickListener {
            selectedOpt(textView1)
        }
        textView2.setOnClickListener {
            selectedOpt(textView2)
        }
        textView3.setOnClickListener {
            selectedOpt(textView3)
        }
        textView4.setOnClickListener {
            selectedOpt(textView4)
        }

    }
}
