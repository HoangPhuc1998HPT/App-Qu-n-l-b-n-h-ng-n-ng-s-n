package com.example.qlnongsan

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("KHACHHANG.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)  // Cho phép ghi trên UI thread
            .build()
        Realm.setDefaultConfiguration(config)
        // Log the location of the Realm file
        Log.d("RealmPath", "Realm file path: ${config.path}")

        // Configuration for NHANVIEN.realm
        val configNhanVien = RealmConfiguration.Builder()
            .name("NHANVIEN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(configNhanVien)
        Log.d("RealmPath", "Realm file path for NHANVIEN: ${configNhanVien.path}")

        val configTTNongSan = RealmConfiguration.Builder()
            .name("TTNONGSAN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(configTTNongSan)
        Log.d("RealmPath", "Realm file path for TTNONGSAN: ${configTTNongSan.path}")
    }
}
