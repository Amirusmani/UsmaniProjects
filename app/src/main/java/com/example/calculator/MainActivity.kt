package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?=null
    var lastnumeric:Boolean=false
    var lastdot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)
    }
    fun onDigit(view: View)
    {
        tvInput?.append((view as Button).text)
        lastnumeric=true
        lastdot=false
    }
    @SuppressLint("SetTextI18n")
    fun onEqual(view: View)
    {
        if(lastnumeric) {
            var tvValue = tvInput?.text.toString()
            var prifix=""
            try {
                if(tvValue.startsWith("-")) {
                    prifix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                } else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
    fun onOperator(view: View) {
        tvInput?.text.let {
            if (lastnumeric && !isOperatorAdded(it.toString()))
                tvInput?.append((view as Button).text)
               lastnumeric = false
               lastdot = false
        }
    }
    private fun removeZero(result:String):String
    {
      var value=result
        if(value.contains(".0"))
        {
            value=result.substring(0,result.length-2)
        }
        return value
    }
    fun onClear(view: View)
    {
        tvInput?.text=""
    }
    fun onDecimalpoint(view: View)
    {
        if(lastnumeric && !lastdot)
        {
            tvInput?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
   private fun isOperatorAdded(value:String):Boolean
    {
                return if(value.startsWith("-"))
                    false
        else
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
    }
}