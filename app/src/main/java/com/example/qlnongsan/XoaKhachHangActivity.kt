package com.example.qlnongsan

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qlnongsan.mode.KhachHang
import io.realm.Realm
import io.realm.kotlin.where

class XoaKhachHangActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xoakhachhang)

        realm = Realm.getDefaultInstance()

        findViewById<Button>(R.id.btnXoaKhachHang).setOnClickListener {
            xoaKhachHang()
        }
    }

    private fun xoaKhachHang() {
        val idKhachHang = findViewById<EditText>(R.id.editIdKhachHang).text.toString()

        if (idKhachHang.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ID Khách Hàng", Toast.LENGTH_SHORT).show()
            return
        }

        realm.executeTransaction { r ->
            val result = r.where<KhachHang>().equalTo("idKhachHang", idKhachHang).findFirst()
            if (result != null) {
                result.deleteFromRealm()
                Toast.makeText(this, "Khách hàng đã được xóa", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Không tìm thấy Khách Hàng", Toast.LENGTH_SHORT).show()
            }
        }
        checkRealmData()
    }
    private fun checkRealmData() {
        val allCustomers = realm.where<KhachHang>().findAll()
        for (customer in allCustomers) {
            Log.d("RealmData", "ID: ${customer.idKhachHang}, Họ: ${customer.ho}, Tên: ${customer.ten}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}