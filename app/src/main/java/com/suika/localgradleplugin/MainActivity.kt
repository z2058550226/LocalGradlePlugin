package com.suika.localgradleplugin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.suika.localgradleplugin.inject.InjectElement
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.e("onCreate()")
        InjectElement()
        Timber.e("onCreate()")
        Toast.makeText(this,"sjfls",Toast.LENGTH_SHORT).show()
    }
}
