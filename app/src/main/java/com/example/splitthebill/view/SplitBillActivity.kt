package com.example.splitthebill.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.splitthebill.R

class SplitBillActivity : AppCompatActivity(){

    var billFull = 0.0
    var numberOfFriends = 0
    var alreadyPaid = 0.0
    var equalDivBill = 0.0
    var individualAmountBill = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.split_bill)
        val bill = findViewById<View>(R.id.billTotalEt) as EditText
        val numberFriends = findViewById<View>(R.id.numberFriendsEt) as EditText
        val paidAlready = findViewById<View>(R.id.valueAlreadyPaidEt) as EditText
        val split = findViewById<View>(R.id.splitBtn) as Button
        split.setOnClickListener(object : View.OnClickListener {
            val billDivEqual = findViewById<View>(R.id.billDivFinalValueTv) as TextView

            @SuppressLint("SetTextI18n")
            val billAmountIndividual = findViewById<View>(R.id.yourBillTv) as TextView
            override fun onClick(view: View) {
                billFull = bill.text.toString().toDouble()
                numberOfFriends = numberFriends.text.toString().toInt()
                alreadyPaid = paidAlready.text.toString().toDouble()
                equalDivBill = billFull / numberOfFriends
                billDivEqual.text = equalDivBill.toString()
                individualAmountBill = alreadyPaid - equalDivBill
                billAmountIndividual.text = individualAmountBill.toString()

            }
        })

        val infoBtn = findViewById<Button>(R.id.informationBtn)
        infoBtn.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        val retBtn = findViewById<Button>(R.id.returnBtn)
        retBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}