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

    private fun setup(product: Product){
        val listLabels = ArrayList<HashMap<String, Float>>()
        product.label0?.let { listLabels.add(it) }
        product.label1?.let { listLabels.add(it) }
        product.label2?.let { listLabels.add(it) }
        product.label3?.let { listLabels.add(it) }
        product.label4?.let { listLabels.add(it) }
        product.label5?.let { listLabels.add(it) }
        product.label6?.let { listLabels.add(it) }
        product.label7?.let { listLabels.add(it) }
        product.label8?.let { listLabels.add(it) }
        product.label9?.let { listLabels.add(it) }

        var strLabels  = ""
        listLabels.forEach{
            val(labelKey, labelValue) = getLabel(it)

            strLabels = "$strLabels\t$labelKey - $labelValue\n"
        }

        val labelTextView:TextView = findViewById(R.id.product_id)
        labelTextView.text = product.id

        val nameTextView:TextView = findViewById(R.id.product_name)
        nameTextView.text = product.name

        val headTextView:TextView = findViewById(R.id.head)
        val headStr = "\t Y: " + product.angleY + "\n" +
                "\t Z: " + product.angleZ + "\n" +
                "\t right eye: " + product.rightEye + "\n" +
                "\t left eye: " + product.leftEye + "\n" +
                "\t smile: " + product.smile + "\n"
        headTextView.text = headStr

        val labelsTextView:TextView = findViewById(R.id.labels)
        labelsTextView.text = strLabels
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
