package com.sayempro.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message") ?: "Null data"
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

}
