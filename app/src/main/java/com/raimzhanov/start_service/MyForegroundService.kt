package com.raimzhanov.start_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.security.Provider

class MyForegroundService: Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("OnCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 0 until 100){
                delay(1000)
                log("Timer $i")
            }
        }

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun log(message:String){
        Log.d("SERVICE", "MyForegroundService : $message")
    }

    companion object{
        private const val EXTRA_START = "start"
        fun newIntent(context: Context):Intent{
            return Intent(context,MyForegroundService::class.java)
        }
    }
}