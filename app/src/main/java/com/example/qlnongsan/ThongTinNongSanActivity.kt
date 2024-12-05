package com.example.qlnongsan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.NongSan
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where

class ThongTinNongSanActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NongSanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thongtinnongsan)

        // Use TTNONGSAN.realm configuration
        val configNongSan = RealmConfiguration.Builder()
            .name("TTNONGSAN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configNongSan)

        recyclerView = findViewById(R.id.recyclerViewNongSan)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val nongSans = realm.where<NongSan>().sort("idNongSan", io.realm.Sort.ASCENDING).findAll()
        adapter = NongSanAdapter(this, nongSans)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
