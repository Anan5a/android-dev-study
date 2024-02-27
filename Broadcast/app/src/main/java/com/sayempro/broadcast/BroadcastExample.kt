package com.sayempro.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BroadcastExample:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Broadcast", "Received a broadcast message")
        Toast.makeText(context, "Power connected!", Toast.LENGTH_LONG).show()
    }
}