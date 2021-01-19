package com.github.sasakitomohiro.readmoretextviewsample

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView

class ReadMoreTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(
    context,
    attrs,
    defStyle
) {
    private var fullText = ""
    private var collapsedText = ""
    private var collapseTextCount = 150
    private var readMoreText = "Read More"
    private var isCollapsed = false
    @ColorInt
    private var readMoreColor = Color.RED

    override fun setText(text: CharSequence?, type: BufferType?) {
        if (isCollapsed) {
            collapsedText = text.toString().substring(0, collapseTextCount) + "…"
        }
        super.setText(if (isCollapsed) setCollapseSpan(collapsedText) else text, type)
    }

    private fun setCollapseSpan(text: String): SpannableStringBuilder {
        val spannableStringBuilder =
            SpannableStringBuilder(text, 0, text.length).append(readMoreText)
        spannableStringBuilder
            .setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        isCollapsed = false
                        setText(fullText)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.color = readMoreColor
                    }
                },
                text.length,
                text.length + readMoreText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        return spannableStringBuilder
    }

    fun collapse() {
        if (isCollapsed) return
        isCollapsed = true
        fullText = text.toString()
        text = fullText
        movementMethod = LinkMovementMethod.getInstance()
    }

    fun setCollapseTextCount(count: Int) {
        collapseTextCount = count
    }

    fun setReadMoreText(text: String) {
        readMoreText = text
    }

    fun setReadMoreColor(@ColorInt color: Int) {
        readMoreColor = color
    }
}
