package com.example.qlnongsan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.NhanVien
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where

class DanhSachNhanVienActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NhanVienAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danhsachnhanvien)

        // Use NHANVIEN.realm configuration
        val configNhanVien = RealmConfiguration.Builder()
            .name("NHANVIEN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configNhanVien)

        recyclerView = findViewById(R.id.recyclerViewNhanVien)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val nhanViens = realm.where<NhanVien>().sort("idNhanVien", io.realm.Sort.ASCENDING).findAll()
        adapter = NhanVienAdapter(this, nhanViens)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
