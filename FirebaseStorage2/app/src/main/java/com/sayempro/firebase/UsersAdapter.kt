package com.sayempro.firebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sayempro.firebase.databinding.UserItemBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UsersAdapter(var context: Context, var userList: ArrayList<User>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    inner class UserViewHolder(val adapterBinding: UserItemBinding) :
        ViewHolder(adapterBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
//        return view
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun getUserId(position: Int): String {
        return userList[position].userId
    }
    fun getUserImageId(position: Int): String {
        return userList[position].profileImageId
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.adapterBinding.textViewAge.text = userList[position].userAge.toString()
        holder.adapterBinding.textViewName.text = userList[position].userName
        holder.adapterBinding.textViewEmail.text = userList[position].userEmail
        val imageUrl = userList[position].profileImage
        Picasso.get().load(imageUrl).into(holder.adapterBinding.imageViewUser, object : Callback {
            override fun onSuccess() {
                holder.adapterBinding.progressBarIV.visibility = View.INVISIBLE
            }

            override fun onError(e: Exception?) {
                Toast.makeText(context, e?.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })

        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, UpdateUserActivity::class.java)
            intent.putExtra("id", userList[position].userId)
            intent.putExtra("email", userList[position].userEmail)
            intent.putExtra("name", userList[position].userName)
            intent.putExtra("age", userList[position].userAge)
            intent.putExtra("url", userList[position].profileImage)
            intent.putExtra("imageId", userList[position].profileImageId)
            context.startActivity(intent)
            Toast.makeText(
                context,
                "Id ${userList[position].userId} was clicked",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}