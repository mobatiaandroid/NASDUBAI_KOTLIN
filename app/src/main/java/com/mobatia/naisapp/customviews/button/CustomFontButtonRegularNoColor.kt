package com.mobatia.naisapp.customviews.button

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class CustomFontButtonRegularNoColor :androidx.appcompat.widget.AppCompatButton{
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // ...
        val type= Typeface.createFromAsset(context.assets,"fonts/SourceSansPro-Regular.otf")
        this.setTypeface(type)
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        val type =
            Typeface.createFromAsset(context.assets, "fonts/SourceSansPro-Regular.otf")
        this.setTypeface(type)
    }
}