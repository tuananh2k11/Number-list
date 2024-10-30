package com.example.list

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var edtNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        edtNumber = findViewById(R.id.edtNumber)
        radioGroup = findViewById(R.id.radioGroup)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            generateNumbers()
        }
    }

    private fun generateNumbers() {
        // Reset error message
        tvError.text = ""

        // Lấy giá trị n từ EditText
        val input = edtNumber.text.toString()
        if (input.isEmpty()) {
            tvError.text = "Vui lòng nhập số!"
            return
        }

        try {
            val n = input.toInt()
            if (n < 0) {
                tvError.text = "Vui lòng nhập số nguyên dương!"
                return
            }

            val numbers = when (radioGroup.checkedRadioButtonId) {
                R.id.rbEven -> getEvenNumbers(n)
                R.id.rbOdd -> getOddNumbers(n)
                R.id.rbSquare -> getSquareNumbers(n)
                else -> {
                    tvError.text = "Vui lòng chọn loại số!"
                    return
                }
            }

            // Hiển thị danh sách số
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter

        } catch (e: NumberFormatException) {
            tvError.text = "Dữ liệu không hợp lệ!"
        }
    }

    // Lấy danh sách số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    // Lấy danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    // Lấy danh sách số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { num ->
            val sqrt = sqrt(num.toDouble()).toInt()
            sqrt * sqrt == num
        }
    }
}