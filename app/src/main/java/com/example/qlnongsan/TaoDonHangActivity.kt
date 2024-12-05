package com.example.qlnongsan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.ChiTietDonHang
import com.example.qlnongsan.mode.DonHang
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class TaoDonHangActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NongSanDonHangAdapter
    private lateinit var textViewTongGia: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taodonhang)

        val configDonHang = RealmConfiguration.Builder()
            .name("DONHANG.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(configDonHang)

        val configNongSan = RealmConfiguration.Builder()
            .name("TTNONGSAN.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        val realmNongSan = Realm.getInstance(configNongSan)

        textViewTongGia = findViewById(R.id.textViewTongGia)

        recyclerView = findViewById(R.id.recyclerViewNongSan)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NongSanDonHangAdapter(this, realmNongSan, ::updateTongGia)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btnThemNongSan).setOnClickListener {
            adapter.addItem()
        }

        findViewById<Button>(R.id.btnThanhToan).setOnClickListener {
            taoDonHang()
        }
    }

    private fun updateTongGia() {
        val items = adapter.getItems()
        val tongGia = items.sumOf { (it.gia ?: 0.0) * (it.soLuong ?: 0) }
        textViewTongGia.text = "Tổng Giá: ${tongGia} ngàn đồng"
    }

    private fun taoDonHang() {
        val idKhachHang = findViewById<EditText>(R.id.editIdKhachHang).text.toString()
        val items = adapter.getItems()
        val tongGia = items.sumOf { (it.gia ?: 0.0) * (it.soLuong ?: 0) }

        if (idKhachHang.isEmpty() || items.any { it.idNongSan.isEmpty() || it.soLuong == null || it.gia == null }) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            realm.executeTransaction { r ->
                val lastDonHang = r.where<DonHang>().sort("idDonHang", io.realm.Sort.DESCENDING).findFirst()
                val newId = if (lastDonHang != null) {
                    val lastId = lastDonHang.idDonHang.substring(2).toInt()
                    "DH%04d".format(lastId + 1)
                } else {
                    "DH0001"
                }

                val donHang = r.createObject<DonHang>(newId)
                donHang.idKhachHang = idKhachHang
                donHang.tongGia = tongGia
                donHang.ngDh = Date()

                for (item in items) {
                    val chiTietDonHang = r.createObject<ChiTietDonHang>()
                    chiTietDonHang.idDonHang = newId
                    chiTietDonHang.idNongSan = item.idNongSan
                    chiTietDonHang.soLuong = item.soLuong
                    chiTietDonHang.gia = item.gia
                    r.copyToRealm(chiTietDonHang)
                }
            }

            Toast.makeText(this, "Đơn hàng đã được tạo", Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: Exception) {
            Toast.makeText(this, "Đã xảy ra lỗi: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
