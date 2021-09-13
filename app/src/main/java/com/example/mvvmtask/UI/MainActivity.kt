package com.example.mvvmtask.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmtask.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fram,ProductsFragment())
            .commitNow()
    }
}