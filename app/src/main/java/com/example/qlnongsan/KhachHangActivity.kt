package com.example.qlnongsan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KhachHangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_khachhang)

        findViewById<Button>(R.id.btnTaoKhachHang).setOnClickListener {
            startActivity(Intent(this, TaoKhachHangActivity::class.java))
        }

        findViewById<Button>(R.id.btnXoaKhachHang).setOnClickListener {
            startActivity(Intent(this, XoaKhachHangActivity::class.java))
        }
        findViewById<Button>(R.id.btnDanhSachKhachHang).setOnClickListener {
            startActivity(Intent(this, DanhSachKhachHangActivity::class.java))
        }
    }
}