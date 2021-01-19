package com.github.sasakitomohiro.readmoretextviewsample

import android.content.Context
import android.graphics.Canvas
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
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
    private var trimmedText = ""
    private var collapseTextCount = 150
    private var collapseLines = Integer.MAX_VALUE
    private var readMoreText = "Read More"
    private var isCollapsed = true

    override fun setText(text: CharSequence?, type: BufferType?) {
        if (isCollapsed) {
            fullText = text.toString()
            trimmedText = fullText.substring(0, collapseTextCount)
        }
        super.setText(if (isCollapsed) setCollapseSpan(trimmedText) else fullText, type)
        if (isCollapsed) movementMethod = LinkMovementMethod.getInstance()
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
                },
                text.length,
                text.length + readMoreText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        return spannableStringBuilder
    }

    fun setCollapseLines(lines: Int) {
        collapseLines = lines
    }

    fun setCollapseTextCount(count: Int) {
        collapseTextCount = count
    }

    fun setReadMoreText(text: String) {
        readMoreText = text
    }

    fun onClickExpand() {
        isCollapsed = false
        maxLines = Integer.MAX_VALUE
    }
}
