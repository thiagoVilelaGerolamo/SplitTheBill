package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splitthebill.R
import com.example.splitthebill.adapter.FriendAdapter
import com.example.splitthebill.model.Friend
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var addFriendBtn: FloatingActionButton
    private lateinit var rv: RecyclerView
    private lateinit var friendList:ArrayList<Friend>
    private lateinit var friendAdapter: FriendAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        friendList = ArrayList()
        addFriendBtn = findViewById(R.id.addFriendBtn)
        rv = findViewById(R.id.friendRv)
        friendAdapter = FriendAdapter(this,friendList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = friendAdapter
        addFriendBtn.setOnClickListener { addInfo() }

        val stbBtn = findViewById<Button>(R.id.splitBillScreenBtn)
        stbBtn.setOnClickListener{
            val intent = Intent(this,SplitBillActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_friend,null)
        val friendName = v.findViewById<EditText>(R.id.friendNameEt)
        val friendValue = v.findViewById<EditText>(R.id.friendValueEt)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val names = friendName.text.toString()
            val value = friendValue.text.toString()
            friendList.add(Friend("Name: $names","Value : $value"))
            friendAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel", Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }
}