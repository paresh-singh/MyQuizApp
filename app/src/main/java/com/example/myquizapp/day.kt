package com.example.myquizapp

import java.util.*
import kotlin.math.roundToInt
import kotlin.text.split as split1


fun dayofweek(d: Int, m: Int, y: Int): String{
    val day = arrayOf("Sunday", "Monday", "Tuesday",
        "Wednesday", "Thursday", "Friday",
        "Saturday")
    val y = y
    val t = intArrayOf(0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4)

    val w = (y + y / 4 - y / 100 + y / 400 + t[m - 1] + d) % 7
    val v = day[w]
    println(v)
    return "$v"

}

// Driver Code
fun main() {
    val ans = dayorder()

}


fun date():String {
    val gc = GregorianCalendar()
    val year = randBetween(1600, 2080)
    val dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR))
    gc[Calendar.DAY_OF_YEAR] = dayOfYear
    print(gc[Calendar.DAY_OF_MONTH])
    println("-" + (gc[Calendar.MONTH] + 1) + "-" + year.toString())
    val date = gc[Calendar.DAY_OF_MONTH]
    val month = gc[Calendar.MONTH] + 1
    val yr = year.toString()
    return "$date/" + "$month/" + "$yr"
}

fun randBetween(start: Int, end: Int): Int {
    return start + (Math.random() * (end - start)).roundToInt().toInt()
}

fun dayorder() : List<String>{
    val qn = date()
    val list2 = qn.split1("/")
    val dd = list2[0].toInt()
    val mm = list2[1].toInt()
    val yy = list2[2].toInt()
    val day: String = dayofweek(d = dd ,m = mm ,y = yy)
    // random date is confirmed , day contains the ans
    val list = arrayListOf<String>("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")

    var ran1 = list.random()
    var ran2 = list.random()
    var ran3 = list.random()
    while (true){
        if (ran1==day){
            ran1 = list.random()
        }else if (ran2==day || ran2==ran1){
            ran2 = list.random()
        }else if (ran3==day || ran3==ran1 || ran3==ran2){
            ran3 = list.random()
        }else {
            if(ran3!=day && ran3!=ran1 && ran3!=ran2 && ran2!=ran1 && ran1!=day && ran2!=day ){
                break
            }
        }
    }
    println(ran1)
    println(ran2)
    println(ran3)
    println(qn)
    return arrayListOf(ran1,ran2,ran3,day,qn)
}