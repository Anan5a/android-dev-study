package com.sayempro.firebase

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.sayempro.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    val database = Firebase.database
    val reference = database.reference.child("MyUsers")
    var userList = ArrayList<User>()
    lateinit var usersAdapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)
        readDataFromDatabase()



        mainBinding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = usersAdapter.getUserId(viewHolder.adapterPosition)
                reference.child(id).removeValue().addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(applicationContext, "User deleted successfully", Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }).attachToRecyclerView(mainBinding.recyclerView)

    }

    fun readDataFromDatabase(){
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (eachUser in snapshot.children){
                    val user = eachUser.getValue(User::class.java)
                    if (user!=null){
                        userList.add(user)
                    }
//                    Log.wtf("firebaseData", user.toString())
                }
                usersAdapter = UsersAdapter(this@MainActivity, userList)
                mainBinding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                mainBinding.recyclerView.adapter = usersAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAllUser->{
                showDialogMessage()
            }
        }
        return true
    }

    fun showDialogMessage(){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Are you sure?")
            .setMessage("All users will be deleted. It cannot be undone.")
            .setCancelable(false)
            .setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                reference.removeValue().addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(applicationContext, "All users deleted successfully", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss()

            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            .create()
            .show()
    }

}