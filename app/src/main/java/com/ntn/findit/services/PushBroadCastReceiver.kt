package com.ntn.findit.services


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ntn.findit.MainActivity
import com.ntn.findit.model.UserNotification
import com.parse.ManifestInfo
import com.parse.ParsePushBroadcastReceiver

class CustomBroadcastReceiver : ParsePushBroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (intent != null) {
                //showNotification(context.applicationContext)
                showNotification(context, intent)
            }
        } else {
            context.applicationContext.startActivity(
                Intent(
                    context,
                    MainActivity::class.java
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
        }
        //PushUtils.showReceiverCalledNotification(context)

    }


    private fun showNotification(context: Context, oldIntent: Intent) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("default", "default", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val pushData = getPushData(oldIntent)
        val title = pushData.optString("title", ManifestInfo.getDisplayName(context))
        val alert = pushData.optString("alert", "Notification received.")
        val user = pushData.optJSONObject("userNotification")

        Log.d("Mio", "User noti recieved: $user")


        val tickerText = String.format("%s: %s", title, alert)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT


        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (user != null) {
            intent.putExtra(
                "userNotification",
                UserNotification(
                    user.getString("username"),
                    user.getDouble("latitude"),
                    user.getDouble("longitude")
                )
            )
        }
        val pContentIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = getNotificationChannel(context, intent)
                createNotificationChannel(context, notificationChannel)
                notificationChannel.id
            } else {
                "default"
            }


        with(NotificationCompat.Builder(context, channelId)) {
            setContentTitle(title)
            setContentText(alert)
            setTicker(tickerText)
            setSmallIcon(super.getSmallIconId(context, intent))
            setLargeIcon(super.getLargeIcon(context, intent))
            setContentIntent(pContentIntent)
            setAutoCancel(true)
            setDefaults(Notification.DEFAULT_ALL)
            manager.notify(87, build())
        }

    }
}