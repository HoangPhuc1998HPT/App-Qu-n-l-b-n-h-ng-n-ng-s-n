package com.example.qlnongsan

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.KhachHang
import io.realm.Realm
import io.realm.kotlin.where

class DanhSachKhachHangActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: KhachHangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danhsachkhachhang)

        realm = Realm.getDefaultInstance()

        recyclerView = findViewById(R.id.recyclerViewKhachHang)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val khachHangs = realm.where<KhachHang>().sort("idKhachHang", io.realm.Sort.DESCENDING).findAll()
        adapter = KhachHangAdapter(this, khachHangs)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
