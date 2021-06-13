package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var selopt = ""
    var count:Int = 0
    lateinit var vib : Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        again()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("key", count)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val userInt : Int = savedInstanceState.getInt("key")
        textView5.text = "Score is " + userInt.toString()

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
        vib = getSystemService(VIBRATOR_SERVICE) as Vibrator
        val lay: ConstraintLayout = findViewById(R.id.mainLayout)
        btnShow.setOnClickListener {
            if (selopt == opt4){
                count++
                Log.i("correct option","option 4 is selected")
                lay.setBackgroundColor(Color.parseColor("#00ff00"))
                val timer = object: CountDownTimer(2000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        lay.setBackgroundResource(R.drawable.bg3)
                    }
                }
                timer.start()
                again()

            }else if(selopt == opt1 || selopt ==opt2 || selopt==opt3 ) {
                var intent = Intent(this, answer::class.java )
                Log.i("wrong option","game over")
                lay.setBackgroundColor(Color.parseColor("#ff0000"))
                vib.vibrate(500)

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

