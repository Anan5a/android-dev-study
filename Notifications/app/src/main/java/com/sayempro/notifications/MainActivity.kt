package com.sayempro.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sayempro.notifications.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID = "343"

    lateinit var binding: ActivityMainBinding
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        binding.buttonNotification.setOnClickListener {
//            val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
//                .setHour(currentHour).setMinute(currentMinute).setTitleText("Set notification time")
//                .build()
//            timePicker.show(supportFragmentManager, "1")
//
//            timePicker.addOnPositiveButtonClickListener {
//                calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
//                calendar.set(Calendar.MINUTE, timePicker.minute)
//                calendar.set(Calendar.SECOND, 0)
//                calendar.set(Calendar.MILLISECOND, 0)
//
//                val intent = Intent(applicationContext, NotificationReceiver::class.java)
//                val pendingIntent = if (Build.VERSION.SDK_INT >= 23) {
//                    PendingIntent.getBroadcast(
//                        applicationContext,
//                        100,
//                        intent,
//                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//                    )
//                } else {
//                    PendingIntent.getBroadcast(
//                        applicationContext, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT
//                    )
//                }
//                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//                alarmManager.setInexactRepeating(
//                    AlarmManager.RTC_WAKEUP,
//                    calendar.timeInMillis,
//                    AlarmManager.INTERVAL_DAY,
//                    pendingIntent
//                )
//                Toast.makeText(applicationContext, "The alarm has been set!", Toast.LENGTH_LONG).show()
//
//            }
            counter++
            binding.buttonNotification.text = "$counter"

            if (counter % 5 == 0) {
                startNotification()
            }

        }


    }

    private fun startNotification() {

        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= 23) {
            PendingIntent.getActivity(
                applicationContext,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        //action button
        val actionIntent = Intent(applicationContext, Receiver::class.java)
        actionIntent.putExtra("message", "This is action from notification!!")
        val actionPendingIntent = if (Build.VERSION.SDK_INT >= 23) {
            PendingIntent.getBroadcast(
                applicationContext,
                1,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getBroadcast(
                applicationContext, 1, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        //dismiss button
        val dismissIntent = Intent(applicationContext, ReceiverDismiss::class.java)
        val dismissPendingIntent = if (Build.VERSION.SDK_INT >= 23) {
            PendingIntent.getBroadcast(
                applicationContext,
                2,
                dismissIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getBroadcast(
                applicationContext, 2, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        }


        val icon = BitmapFactory.decodeResource(resources,R.drawable.picture_a)
        val text = resources.getString(R.string.big_text)



        val builder = NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, "1", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            builder.setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Title of notification").setContentText("Notification content: ")
                .setContentIntent(pendingIntent).setAutoCancel(true)
                .addAction(R.drawable.ic_notification, "Toast message", actionPendingIntent)
                .addAction(android.R.drawable.alert_dark_frame, "Dismiss", dismissPendingIntent)
                .setLargeIcon(icon)
//                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon).bigLargeIcon(null as Bitmap?))
                .setStyle(NotificationCompat.BigTextStyle().bigText(text).setSummaryText("This is a summary"))

        } else {
            builder.setSmallIcon(R.drawable.ic_notification).setContentTitle("Notification Title")
                .setContentText("This is a notification text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_notification, "Toast message", actionPendingIntent)
                .addAction(android.R.drawable.alert_dark_frame, "Dismiss", dismissPendingIntent)
                .setLargeIcon(icon)
//                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon).bigLargeIcon(null as Bitmap?))
                .setStyle(NotificationCompat.BigTextStyle().bigText(text).setSummaryText("This is a summary"))


        }
        val notificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(1, builder.build())
    }
}