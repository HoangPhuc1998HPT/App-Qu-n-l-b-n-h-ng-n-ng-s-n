package com.example.qlnongsan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.DonHang
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where

class ThongTinDonHangActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DonHangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thongtindonhang)

        // Use DONHANG.realm configuration
        val configDonHang = RealmConfiguration.Builder()
            .name("DONHANG.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configDonHang)

        recyclerView = findViewById(R.id.recyclerViewDonHang)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val donHangs = realm.where<DonHang>().sort("idDonHang", io.realm.Sort.ASCENDING).findAll()
        adapter = DonHangAdapter(this, donHangs)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
