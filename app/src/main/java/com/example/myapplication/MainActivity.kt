package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentNumber: String = ""
    private var operator: String? = null
    private var firstNumber: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        setNumberButtonListeners()
        setOperatorButtonListeners()
    }

    private fun setNumberButtonListeners() {
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btnDot
        )
        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { button ->
                val text = (button as Button).text.toString()
                if (text == "." && currentNumber.contains(".")) return@setOnClickListener
                currentNumber += text
                resultTextView.text = currentNumber
            }
        }
    }

    private fun setOperatorButtonListeners() {
        val operators = listOf(R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnEquals, R.id.btnClear)
        for (id in operators) {
            findViewById<Button>(id).setOnClickListener { button ->
                when (button.id) {
                    R.id.btnClear -> clear()
                    R.id.btnEquals -> calculate()
                    else -> {
                        operator = (button as Button).text.toString()
                        firstNumber = currentNumber.toDoubleOrNull() ?: 0.0
                        currentNumber = ""
                    }
                }
            }
        }
    }

    private fun clear() {
        currentNumber = ""
        operator = null
        firstNumber = 0.0
        resultTextView.text = "0"
    }

    private fun calculate() {
        val secondNumber = currentNumber.toDoubleOrNull() ?: return
        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else "Error"
            else -> "Error"
        }
        resultTextView.text = result.toString()
        currentNumber = result.toString()
        operator = null
    }
}