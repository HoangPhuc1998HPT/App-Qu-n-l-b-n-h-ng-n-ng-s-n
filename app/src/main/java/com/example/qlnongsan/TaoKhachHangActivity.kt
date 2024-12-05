package com.example.qlnongsan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qlnongsan.mode.KhachHang
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class TaoKhachHangActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taokhachhang)
        Realm.init(this)
        realm = Realm.getDefaultInstance()
        findViewById<Button>(R.id.btnTaoKhachHang).setOnClickListener {
            taoKhachHang()
        }
    }

    private fun taoKhachHang() {
        val ho = findViewById<EditText>(R.id.editHo).text.toString()
        val ten = findViewById<EditText>(R.id.editTen).text.toString()
        val email = findViewById<EditText>(R.id.editEmail).text.toString()
        val sdt = findViewById<EditText>(R.id.editSDT).text.toString()
        val diaChi = findViewById<EditText>(R.id.editDiaChi).text.toString()

        if (ho.isEmpty() || ten.isEmpty() || email.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val lastCustomer = realm.where<KhachHang>().sort("idKhachHang", io.realm.Sort.DESCENDING).findFirst()
        val newId = if (lastCustomer != null) {
            val lastId = lastCustomer.idKhachHang.substring(2).toInt()
            "ID%04d".format(lastId + 1)
        } else {
            "ID0001"
        }

        realm.executeTransaction { r ->
            val khachHang = r.createObject<KhachHang>(newId)
            khachHang.ho = ho
            khachHang.ten = ten
            khachHang.email = email
            khachHang.sdt = sdt
            khachHang.diaChi = diaChi
            khachHang.ngayTao = Date()
        }

        Toast.makeText(this, "Khách hàng đã được tạo", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
