package com.mobatia.naisapp.customviews.textview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.mobatia.naisapp.R

class CustomFontTextViewSemiBoldWhite : androidx.appcompat.widget.AppCompatTextView{
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // ...
        val type= Typeface.createFromAsset(context.assets,"fonts/SourceSansPro-SemiBold.otf")
        this.setTypeface(type)
        this.setTextColor(resources.getColor(R.color.white))
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        val type =
            Typeface.createFromAsset(context.assets, "fonts/SourceSansPro-SemiBold.otf")
        this.setTypeface(type)
        this.setTextColor(context.resources.getColor(R.color.white))
    }
}