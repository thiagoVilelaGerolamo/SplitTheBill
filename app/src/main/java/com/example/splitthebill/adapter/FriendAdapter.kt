package com.example.splitthebill.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.splitthebill.R
import com.example.splitthebill.model.Friend

class FriendAdapter(val c: Context, val friendList:ArrayList<Friend>): RecyclerView.Adapter<FriendAdapter.FriendViewHolder>()
{



    inner class FriendViewHolder(val v: View): RecyclerView.ViewHolder(v){
        var fName: TextView
        var fValue: TextView
        var fMenus: ImageView

        init {
            fName = v.findViewById<TextView>(R.id.titleTv)
            fValue = v.findViewById<TextView>(R.id.subTitleTv)
            fMenus = v.findViewById(R.id.menusIm)
            fMenus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v: View) {
            val position = friendList[adapterPosition]
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editFriend->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_friend,null)
                        val name = v.findViewById<EditText>(R.id.friendNameEt)
                        val value = v.findViewById<EditText>(R.id.friendValueEt)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                    dialog,_->
                                position.friendName = name.text.toString()
                                position.friendValue = value.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c,"User Information was edited succefuly", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel"){
                                    dialog,_->
                                dialog.dismiss()

                            }
                            .create()
                            .show()

                        true
                    }
                    R.id.delete->{
                        /**set delete*/
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Delete is permanent, are you sure?")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                friendList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"Friend Deleted", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else-> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v  = inflater.inflate(R.layout.list_friend,parent,false)
        return FriendViewHolder(v)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val newList = friendList[position]
        holder.fName.text = newList.friendName
        holder.fValue.text = newList.friendValue
    }

    override fun getItemCount(): Int {
        return  friendList.size
    }
}