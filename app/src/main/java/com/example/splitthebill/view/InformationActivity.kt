package com.example.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.splitthebill.R

class InformationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information_activity)


        val retSplitBtn = findViewById<Button>(R.id.returnSplitBtn)
        retSplitBtn.setOnClickListener {
            val intent = Intent(this, SplitBillActivity::class.java)
            startActivity(intent)
        }
    }
}