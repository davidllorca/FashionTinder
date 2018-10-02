package com.example.fashiontinder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CardInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_info)

        var labelTextView:TextView = findViewById(R.id.product_labels)
        labelTextView.text = "label1 - smile: 0,7 \n label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n" +
                " label1 - smile: 0,7 \n"
    }

    fun setup(product)
}
