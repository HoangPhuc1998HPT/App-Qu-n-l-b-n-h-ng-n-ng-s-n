package com.example.qlnongsan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NhanVienActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhanvien)

        findViewById<Button>(R.id.btnTaoNhanVien).setOnClickListener {
            startActivity(Intent(this, TaoNhanVienActivity::class.java))
        }

        findViewById<Button>(R.id.btnXoaNhanVien).setOnClickListener {
            startActivity(Intent(this, XoaNhanVienActivity::class.java))
        }

        findViewById<Button>(R.id.btnDanhSachNhanVien).setOnClickListener {
            startActivity(Intent(this, DanhSachNhanVienActivity::class.java))
        }
    }
}