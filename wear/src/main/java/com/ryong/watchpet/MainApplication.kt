package com.ryong.watchpet

import android.app.Application
import com.ryong.watchpet.services.HealthServicesRepository

const val TAG = "WatchPet WearOS Application"
const val PERMISSION = android.Manifest.permission.BODY_SENSORS

class MainApplication : Application() {
    val healthServicesRepository by lazy { HealthServicesRepository(this) }
}