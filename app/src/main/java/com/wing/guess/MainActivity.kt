package com.wing.guess

//新版, 之前叫import android.support.v7.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //R = com.wing.guess.R.xxx
        setContentView(R.layout.activity_main)

    }

}
