package com.example.fashiontinder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Juan Fuentes on 2/10/18.
 */

@Parcelize
class Product(var id:String? = null,
              var name:String? = null,
              var image:String? = null,
              var angleY:Float? = null,
              var angleZ:Float? = null,
              var likes:Int = 0,
              var dislikes:Int =  0,
              var leftEye:Float? = null,
              var rightEye:Float? = null,
              var smile:Float? =null,
              var label0:HashMap<String, Float>? =null,
              var label1:HashMap<String, Float>? =null,
              var label2:HashMap<String, Float>? =null,
              var label3:HashMap<String, Float>? =null,
              var label4:HashMap<String, Float>? =null,
              var label5:HashMap<String, Float>? =null,
              var label6:HashMap<String, Float>? =null,
              var label7:HashMap<String, Float>? =null,
              var label8:HashMap<String, Float>? =null,
              var label9:HashMap<String, Float>? =null
              ) : Parcelable