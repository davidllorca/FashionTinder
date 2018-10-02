package com.example.fashiontinder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Juan Fuentes on 2/10/18.
 */
@Parcelize
class Response(var response: HashMap<String, Product>? = null) : Parcelable {



}