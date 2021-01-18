package com.github.sasakitomohiro.readmoretextviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.github.sasakitomohiro.readmoretextviewsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.textView.text = "ReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextViewReadMoreTextView"
    }
}
