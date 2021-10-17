package com.sharkawy.android_cicd

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create #1 Push On Master

        //Create #1 Pull Request On Dev-CI Branch

        Toast.makeText(this, "Hey! Firebase distributions integrated successfully! :)", Toast.LENGTH_SHORT).show()
    }
}