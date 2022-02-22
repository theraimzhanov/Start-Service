package com.raimzhanov.start_service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.raimzhanov.start_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var id = 0

    private val binding  by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.simpleService.setOnClickListener {
            startService(MyService.newIntent(this,25))
        }
        binding.foregroundService.setOnClickListener {
            ContextCompat.startForegroundService(this
                ,MyForegroundService.newIntent(this))
        }
    }

    private fun showNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("title")
            .setContentText("text")
            .setSmallIcon(R.drawable.icon)
            .build()

    notificationManager.notify(id,notification)
    }
    companion object{
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "Notification Service"
    }
}