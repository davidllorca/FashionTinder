package com.example.fashiontinder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CardInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_info)
        val intent = getIntent()
        val product = intent.getParcelableExtra<Product>("product")

        setup(product)
    }

    fun setup(product: Product){
        var labelString: String

        val(label0Key, label0Value) = getLabel(product.label0)
        val(label1Key, label1Value) = getLabel(product.label1)
        val(label2Key, label2Value) = getLabel(product.label2)
        val(label3Key, label3Value) = getLabel(product.label3)
        val(label4Key, label4Value) = getLabel(product.label4)
        val(label5Key, label5Value) = getLabel(product.label5)
        val(label6Key, label6Value) = getLabel(product.label6)
        val(label7Key, label7Value) = getLabel(product.label7)
        val(label8Key, label8Value) = getLabel(product.label8)
        val(label9Key, label9Value) = getLabel(product.label9)


        labelString = "Product id: " + product.id + "\n" +
                "Product name: " + product.name + "\n" +
                "Head Inclination: \n" +
                    "\t Y: " + product.angleY + "\n" +
                    "\t Z: " + product.angleZ + "\n" +
                    "\t right eye: " + product.rightEye + "\n" +
                    "\t left eye: " + product.leftEye + "\n" +
                    "\t smile: " + product.smile + "\n" +
                "Labels: \n" +
                    "\t" + label0Key + " - " + label0Value + "\n" +
                    "\t" + label1Key + " - " + label1Value + "\n" +
                    "\t" + label2Key + " - " + label2Value + "\n" +
                    "\t" + label3Key + " - " + label3Value + "\n" +
                    "\t" + label4Key + " - " + label4Value + "\n" +
                    "\t" + label5Key + " - " + label5Value + "\n" +
                    "\t" + label6Key + " - " + label6Value + "\n" +
                    "\t" + label7Key + " - " + label7Value + "\n" +
                    "\t" + label8Key + " - " + label8Value + "\n" +
                    "\t" + label9Key + " - " + label9Value + "\n"


        val labelTextView:TextView = findViewById(R.id.product_labels)
        labelTextView.text = labelString
    }

    fun getLabel(map: HashMap<String, Float>?): Pair<String, Float>{
        var labelKey: String = ""
        var keyValue: Float = 0F
        for (key in map!!.keys){
            labelKey = key
            keyValue = map.get(key)!!
        }

        return Pair(labelKey, keyValue)
    }

}
