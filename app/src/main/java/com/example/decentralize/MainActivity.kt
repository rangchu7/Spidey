package com.example.decentralize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.decentralize.activities.AllocateData
import com.example.decentralize.activities.HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val homeIntent = Intent(this, HomeActivity :: class.java)
        startActivity(homeIntent)
        finish()*/


        val allocateDataIntent =  Intent(this, AllocateData :: class.java)
        startActivity(allocateDataIntent)
        finish()

    }



}
